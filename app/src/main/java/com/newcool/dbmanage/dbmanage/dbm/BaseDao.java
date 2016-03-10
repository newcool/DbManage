package com.newcool.dbmanage.dbmanage.dbm;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by niuxuan on 2016/3/10.
 */
public class BaseDao implements Dao {
    private SQLiteDatabase db;
    public BaseDao(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public <T> void insert(T t) {

        Class<?> clazz = t.getClass();
        if(clazz != t.getClass()) return;



        StringBuilder builder = new StringBuilder("insert into ");

        StringBuilder placeholders = new StringBuilder();
        ArrayList<Object> paramList = new ArrayList<>();

        if(clazz.isAnnotationPresent(Table.class)){
            Table table = (Table) clazz.getAnnotation(Table.class);
            if(table!=null){
                builder.append(table.value()+" (");
            }
        }

        Field[] fields = clazz.getDeclaredFields();
        for(int i = 0;i<fields.length;i++){

            Field f = fields[i];
            if(f.isAnnotationPresent(Column.class)){
                Column column = f.getAnnotation(Column.class);
                builder.append(column.value());
                placeholders.append("?");
                if(!(i == (fields.length-1))){
                    builder.append(",");
                    placeholders.append(",");
                }
                String fieldName = f.getName();
                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                String getter = "get" + firstLetter + fieldName.substring(1);
                Method method = null;
                try {
                    method = t.getClass().getMethod(getter, new Class[] {});
                    Object value = method.invoke(t, new Object[] {});
                    paramList.add(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }

        builder.append(") values (").append(placeholders).append(")");
        Log.i("niu", builder.toString());
        db.execSQL(builder.toString(), paramList.toArray());
    }

    @Override
    public void delete() {

    }
}
