package com.newcool.dbmanage.dbmanage.dbm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 34721_000 on 2016/3/9.
 */
public class DbOpenHelper extends SQLiteOpenHelper{



    public DbOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "test";


    @Override
    public void onCreate(SQLiteDatabase db) {

        onUpgrade(db,db.getVersion(),1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(int version = oldVersion+1; version<=newVersion;version++){
            switch (version){
                case 1:
                    db.execSQL("create table tt_1(id int primary key not null,name varchar(10),tel_number integer)");
                    break;
            }
        }
    }

    public void upgradeTo(int version){
        switch (version){
            case 1:

                break;
        }
    }


}
