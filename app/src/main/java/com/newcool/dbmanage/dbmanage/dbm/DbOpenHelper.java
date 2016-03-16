package com.newcool.dbmanage.dbmanage.dbm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 34721_000 on 2016/3/9.
 */
public class DbOpenHelper extends SQLiteOpenHelper{



    public DbOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        Log.i("niu","contruct");
    }

    public static final int DB_VERSION = 2;
    public static final String DB_NAME = "test";


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("niu","onCreate");
        onUpgrade(db,db.getVersion(),DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(int version = oldVersion+1; version<=newVersion;version++){
            switch (version){
                case 1:
                    db.execSQL("create table tt_1(id int not null,name varchar(10),tel_number varchar(20))");
                    db.execSQL("create table t_class(id int not null auto_increment,s_num varchar(10) not null unique,name_n varchar(10),tel_number_n varchar(20))");
                    db.execSQL("create table t_student(id int not null auto_increment,name varchar(6) not null,,FOREIGN KEY (classid) REFERENCES classes(id))");
                    break;
                case 2:
                    db.execSQL("alter table tt_1 add age varchar(20)");
            }
        }
    }

}
