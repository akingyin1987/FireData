package com.com.zlcd.firedata.activity;

/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import com.com.zlcd.firedata.MyApp;
import com.com.zlcd.firedata.R;
import com.com.zlcd.firedata.adapter.ListAdapter;
import com.com.zlcd.firedata.adapter.MeterListAdapter;
import com.com.zlcd.firedata.db.Cbjl;
import com.com.zlcd.firedata.db.DatabaseContext;
import com.com.zlcd.firedata.db.DatabaseHelper;
import com.com.zlcd.firedata.db.DbUtil;
import java.util.List;

/**
 * @ Description:
 *
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @ Date 2016/5/10 17:50
 * @ Version V1.0
 */
public class MeterListActivity  extends AppCompatActivity{

  String  cbcid;

  RecyclerView recyclerView;

  public MeterListAdapter adapter;



  SQLiteDatabase db;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_index);
    findViewById(R.id.menu_yellow).setVisibility(View.GONE);
    if(null != getSupportActionBar()){
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setHomeButtonEnabled(true);
    }
    db = MyApp.getIntense().getDb();
    if(null == savedInstanceState){
       cbcid = getIntent().getStringExtra("cbcid");
    }else{
      cbcid = savedInstanceState.getString("cbcid");
    }

    recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    try {
      List<Cbjl>   items = DbUtil.findAllCbjlByCbcId(cbcid,db);
      adapter = new MeterListAdapter(this,items,R.layout.list_item);
      recyclerView.setAdapter(adapter);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString("cbcid",cbcid);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getItemId() == android.R.id.home){
      finish();
    }
    return super.onOptionsItemSelected(item);
  }
}
