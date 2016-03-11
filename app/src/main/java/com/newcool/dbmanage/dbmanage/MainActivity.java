package com.newcool.dbmanage.dbmanage;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.newcool.dbmanage.dbmanage.dbm.BaseDao;
import com.newcool.dbmanage.dbmanage.dbm.DbOpenHelper;
import com.newcool.dbmanage.dbmanage.dbm.ItemBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private TextView tv_create,tv_inert_one,tv_findall,tv_deleteall,tv_findby;
    DbOpenHelper helper = null;
    SQLiteDatabase db = null;
    BaseDao dao = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_create = (TextView) findViewById(R.id.tv_create);
        tv_inert_one = (TextView) findViewById(R.id.tv_inert_one);
        tv_findall = (TextView) findViewById(R.id.tv_findall);

        tv_deleteall = (TextView) findViewById(R.id.tv_deleteall);
        tv_findby = (TextView) findViewById(R.id.tv_findby);

        tv_deleteall.setOnClickListener(this);
        tv_findall.setOnClickListener(this);
        tv_create.setOnClickListener(this);
        tv_inert_one.setOnClickListener(this);
        tv_findby.setOnClickListener(this);

        helper = new DbOpenHelper(this);
        db = helper.getWritableDatabase();
        dao = new BaseDao(db);
    }

    int i = 4;
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_create:

                break;
            case R.id.tv_inert_one:


                ItemBean item = new ItemBean(i+"","niuniu","123456","12");
                i++;
                dao.insert(item);
                break;
            case  R.id.tv_findall:

                List<ItemBean> list = dao.findAll(ItemBean.class);
                Log.i("niu",list.size()+"");
                for (ItemBean bean:list){
                    Log.i("niu_bean",bean.toString());
                }
                break;
            case R.id.tv_deleteall:

                dao.delete(ItemBean.class);
                break;
            case R.id.tv_findby:
                Map<String,String> map = new HashMap<>();
                map.put("id","5");
                dao.findBy(ItemBean.class,map);
                List<ItemBean> list2 = dao.findAll(ItemBean.class);
                Log.i("niu",list2.size()+"");
                for (ItemBean bean:list2){
                    Log.i("niu_bean",bean.toString());
                }
                break;
        }
    }
}
