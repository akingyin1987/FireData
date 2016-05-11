package com.com.zlcd.firedata.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.com.zlcd.firedata.MyApp;
import com.com.zlcd.firedata.R;
import com.com.zlcd.firedata.adapter.ListAdapter;
import com.com.zlcd.firedata.callback.ApiCallBack;
import com.com.zlcd.firedata.db.Cbc;
import com.com.zlcd.firedata.db.DatabaseContext;
import com.com.zlcd.firedata.db.DatabaseHelper;
import com.com.zlcd.firedata.db.DbUtil;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.List;
import org.byteam.superadapter.OnItemClickListener;

/**
 * Created by Administrator on 2016/5/8.
 */
public class IndexActivity  extends AppCompatActivity {

    RecyclerView   recyclerView;

    public ListAdapter  adapter;

    SQLiteDatabase   db;

    FloatingActionMenu menu;

    FloatingActionButton  fab12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

         db = MyApp.getIntense().getDb();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        menu = (FloatingActionMenu)findViewById(R.id.menu_yellow);
        menu.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 menu.toggle(true);
            }
        });
        fab12  = (FloatingActionButton)findViewById(R.id.fab12);
        fab12.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            showSettingDialog();
          }
        });
        try {

            List<Cbc>  items = DbUtil.findAllCbc(db);
            System.out.println(null == items);
           if(null != items){
               System.out.println(items.size());
           }
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ListAdapter(this,items,R.layout.list_item);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnScrollListener(new HidingScrollListener() {
              @Override public void onHide() {
                 menu.hideMenu(true);
              }

              @Override public void onShow() {
                 menu.showMenu(true);
              }
            });
            adapter.setOnItemClickListener(new OnItemClickListener() {
              @Override public void onItemClick(View itemView, int viewType, int position) {
                Cbc   cbc = adapter.getItem(position);
                Intent   intent = new Intent(IndexActivity.this,MeterListActivity.class);
                intent.putExtra("cbcid",cbc.cbcid);
                startActivity(intent);
              }
            });
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



    public   void   showSettingDialog(){
      final SharedpreferencesUtil  pref = new SharedpreferencesUtil(this);
      final String  key_name=pref.getStringData("key_name");
      final String  key_port=pref.getStringData("key_port");
      final String  key_http=pref.getStringData("key_http");
      View   custoview = LayoutInflater.from(this).inflate(R.layout.web_setting,null);
      final EditText   name = (EditText)custoview.findViewById(R.id.edit_name);
      final EditText   port  = (EditText)custoview.findViewById(R.id.edit_port);
      final EditText   http  = (EditText)custoview.findViewById(R.id.edit_ip);
      name.setText(key_name);
      port.setText(key_port);
      http.setText(key_http);
      new MaterialDialog.Builder(this)
          .customView(custoview,true)
          .positiveText("确定")
          .negativeText("取消")
          .onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
               String   keyname = name.getText().toString().trim();
               String   keyprot = port.getText().toString().trim();
               String   keyhttp = http.getText().toString().trim();
               pref.saveData("key_name",keyname);
               pref.saveData("key_port",keyprot);
               pref.saveData("key_http",keyhttp);
               showToash("保存成功");
            }
          })
          .onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
          }).show();
    }


    public   void   showToash(String message){
      Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //if(null != db){
        //    db.close();
        //}
    }

  private long exitTime = 0;

  public void doExitApp() {
    if ((System.currentTimeMillis() - exitTime) > 2000) {
      Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
      exitTime = System.currentTimeMillis();
    } else {
      android.os.Process.killProcess(android.os.Process.myPid());
      System.exit(0);
      finish();
    }
  }

  @Override
  public void onBackPressed() {
    if(menu.isOpened()){
      menu.close(true);
    }else{
      doExitApp();
    }

  }
}
