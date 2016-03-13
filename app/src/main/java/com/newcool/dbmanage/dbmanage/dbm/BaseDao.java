package com.newcool.dbmanage.dbmanage.dbm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by niuxuan on 2016/3/10.
 */
public class BaseDao<T> implements Dao<T> {
    private SQLiteDatabase db;

    public BaseDao(SQLiteDatabase db) {
        this.db = db;
    }


    public <T> void insert(T t) {

        Class<?> clazz = t.getClass();
        if (clazz != t.getClass()) return;


        StringBuilder builder = new StringBuilder("insert into ");

        StringBuilder placeholders = new StringBuilder();
        ArrayList<Object> paramList = new ArrayList<>();

        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = (Table) clazz.getAnnotation(Table.class);
            if (table != null) {
                builder.append(table.value() + " (");
            }
        }

        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {

            Field f = fields[i];
            if (f.isAnnotationPresent(Column.class)) {
                Column column = f.getAnnotation(Column.class);
                builder.append(column.value());
                placeholders.append("?");
                if (!(i == (fields.length - 1))) {
                    builder.append(",");
                    placeholders.append(",");
                }
                String fieldName = f.getName();
                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                String getter = "get" + firstLetter + fieldName.substring(1);
                Method method = null;
                try {
                    method = t.getClass().getMethod(getter, new Class[]{});
                    Object value = method.invoke(t, new Object[]{});
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
    public List<T> findAll(Class<T> clazz) {

        StringBuilder sql = new StringBuilder("select * from ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = (Table) clazz.getAnnotation(Table.class);
            if (table != null) {
                sql.append(table.value());
            }
        }
        Cursor cursor = db.rawQuery(sql.toString(), null);
        return getBeanListFromCursor(clazz,cursor);
    }

    /**
     * 将结果集转化成list对象
     * @param clazz
     * @param cursor
     * @return
     */
    private List<T> getBeanListFromCursor(Class<T> clazz,Cursor cursor) {
        Field[] fields = clazz.getDeclaredFields();
        List<T> list = new ArrayList<>();
        if (cursor != null) {
            int x = 0;
            while (cursor.moveToNext()) {
                T t = null;
                Log.i("niu_x",x+"");
                x++;
                try {
                    t = clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < fields.length; i++) {
                    Field f = fields[i];

                    if (f.isAnnotationPresent(Column.class)) {
                        Column column = f.getAnnotation(Column.class);
                        String fieldName = f.getName();
                        String firstLetter = fieldName.substring(0, 1).toUpperCase();
                        String setter = "set" + firstLetter + fieldName.substring(1);
                        Log.i("niu_set", setter);
                        Method method = null;

                        try {
                            method = clazz.getDeclaredMethod(setter, String.class);
                            int columnIndex = cursor.getColumnIndex(column.value());
                            String str = cursor.getString(columnIndex);

                            Object bj = method.invoke(t, str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                list.add(t);
            }
            cursor.close();
        }
        return  list;
    }


    @Override
    public void delete(Class<T> clazz) {

        StringBuilder sql = new StringBuilder("delete from ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = (Table) clazz.getAnnotation(Table.class);
            if (table != null) {
                sql.append(table.value());
            }
        }
        db.execSQL(sql.toString());
    }


    @Override
    public List<T> findBy(Class<T> clazz, Map<String,String> map) {

        List<T> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select *");

        if (clazz.isAnnotationPresent(Table.class)){
            Table table = clazz.getAnnotation(Table.class);
            sql.append(" from "+table.value()+" where ");
        }
        StringBuilder where = new StringBuilder("");
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry<String, String>) it.next();

            where.append(entry.getKey()+"="+entry.getValue()+" and ");
        }

        sql.append(where.substring(0, where.length() - 4));

        Log.i("niu_queryBY",sql.toString());
     //           Cursor cursor = db.rawQuery("select * from tt_1 where id<5",null);
       Cursor cursor = db.rawQuery(sql.toString()+";",null);
        return getBeanListFromCursor(clazz,cursor);
    }

    public void deleteBy(Class<T> clazz,Map<String,String> map){
        StringBuilder sql = new StringBuilder("delete from ");

        StringBuilder params = new StringBuilder("");

        if (clazz.isAnnotationPresent(Table.class)){
            Table table = clazz.getAnnotation(Table.class);
            sql.append(table.value()+" where ");
        }

        StringBuilder where = new StringBuilder("");
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry<String, String>) it.next();

            where.append(entry.getKey()+"="+entry.getValue()+" and ");
        }
        sql.append(where.substring(0, where.length() - 4));

        Log.i("niu_deleteBy",sql.toString());
        db.execSQL(sql.toString());
    }
}
