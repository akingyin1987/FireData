package com.com.zlcd.firedata.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.com.zlcd.firedata.MyApp;
import com.com.zlcd.firedata.R;
import com.com.zlcd.firedata.adapter.ListAdapter;
import com.com.zlcd.firedata.callback.ApiCallBack;
import com.com.zlcd.firedata.db.Cbc;
import com.com.zlcd.firedata.db.DatabaseContext;
import com.com.zlcd.firedata.db.DatabaseHelper;
import com.com.zlcd.firedata.db.DbUtil;
import com.github.clans.fab.FloatingActionMenu;

import java.util.List;


/**
 * Created by Administrator on 2016/5/8.
 */
public class IndexActivity  extends AppCompatActivity {

    RecyclerView   recyclerView;

    public ListAdapter  adapter;

    DatabaseHelper    dbhelp;

    SQLiteDatabase   db;

    FloatingActionMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
         DatabaseContext   databaseContext = new DatabaseContext(this);
         dbhelp = new DatabaseHelper(databaseContext);
         db = dbhelp.getWritableDatabase();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        menu = (FloatingActionMenu)findViewById(R.id.menu_yellow);
        menu.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 menu.toggle(true);
            }
        });
        findViewById(R.id.fab12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cbc  cbc =  adapter.getItem(0);
                if(null != cbc){
                    cbc.cbjhms="Test";
                    System.out.println(DbUtil.updateCbc(cbc,db));
                    adapter.clear();
                    adapter.addAll(DbUtil.findAllCbc(db));
                }
            }
        });
        try {
            System.out.println("fffffffffffffffff");


            List<Cbc>  items = DbUtil.findAllCbc(db);
            System.out.println(null == items);
           if(null != items){
               System.out.println(items.size());
           }
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ListAdapter(this,items,R.layout.list_item);
            recyclerView.setAdapter(adapter);
            adapter.setCallBack(new ApiCallBack<Cbc>() {
                @Override
                public void call(Cbc cbc, int postion) {

                }
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != db){
            db.close();
        }
    }
}
