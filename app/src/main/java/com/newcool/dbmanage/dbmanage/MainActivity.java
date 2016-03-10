package com.newcool.dbmanage.dbmanage;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.newcool.dbmanage.dbmanage.dbm.BaseDao;
import com.newcool.dbmanage.dbmanage.dbm.DbOpenHelper;
import com.newcool.dbmanage.dbmanage.dbm.ItemBean;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private TextView tv_create,tv_inert_one;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_create = (TextView) findViewById(R.id.tv_create);
        tv_inert_one = (TextView) findViewById(R.id.tv_inert_one);
        tv_create.setOnClickListener(this);
        tv_inert_one.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        DbOpenHelper helper = null;
        SQLiteDatabase db = null;
        switch (v.getId()){
            case R.id.tv_create:
                helper = new DbOpenHelper(this);
                break;
            case R.id.tv_inert_one:
                if(helper == null){
                    helper = new DbOpenHelper(this);
                }
                db = helper.getWritableDatabase();
                BaseDao dao = new BaseDao(db);
                ItemBean item = new ItemBean("2","niuniu","123456","12");
                dao.insert(item);
                break;
        }
    }
}
