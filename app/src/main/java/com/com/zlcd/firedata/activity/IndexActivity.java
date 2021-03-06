package com.com.zlcd.firedata.activity;

import android.app.FragmentTransaction;
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

import java.util.ArrayList;
import java.util.List;
import org.byteam.superadapter.OnItemClickListener;
import zhou.tools.fileselector.FileSelectorDialog;
import zhou.tools.fileselector.config.FileConfig;
import zhou.tools.fileselector.utils.FileFilter;
import zhou.tools.fileselector.utils.FileType;

/**
 * Created by Administrator on 2016/5/8.
 */
public class IndexActivity  extends AppCompatActivity {

    RecyclerView   recyclerView;

    public ListAdapter  adapter;

    SQLiteDatabase   db;

    FloatingActionMenu menu;

    FloatingActionButton  fab12,fab22,fab32;
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

        fab22 = (FloatingActionButton)findViewById(R.id.fab22);
        fab22.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            showFileSelectDialog();
          }
        });
        fab32 = (FloatingActionButton)findViewById(R.id.fab32);
        fab32.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            showDbFilePathDialog();
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



    public   void   showDbFilePathDialog(){
      SharedpreferencesUtil  pref = new SharedpreferencesUtil(this);
      String   dbfilepath = pref.getStringData("dbpath");
      new MaterialDialog.Builder(this)
          .title("DB路径")
          .content(dbfilepath)
          .positiveText("关闭")

          .show();
    }

    public   void   showFileSelectDialog(){
      menu.toggle(true);
      FileConfig  config = new FileConfig();
      config.selectType = FileType.FILE;
      config.filterModel= FileFilter.FILTER_CUSTOM;
      config.filter=new String[]{"db"};
      FileSelectorDialog fileDialog = new FileSelectorDialog();
      //设置文件选择完成后的回调事件
      fileDialog.setOnSelectFinish(new FileSelectorDialog.OnSelectFinish() {
        @Override
        public void onSelectFinish(ArrayList<String> paths) {
          if(null != paths && paths.size()==1){
            SharedpreferencesUtil  pref = new SharedpreferencesUtil(IndexActivity.this);
            pref.saveData("dbpath",paths.get(0));
            MyApp.getIntense().initSqliteDb();
            db = MyApp.getIntense().getDb();
            List<Cbc>  items = DbUtil.findAllCbc(db);
            adapter.clear();
            adapter.addAll(items);
          }

        }
      });
      //传递配置信息
      Bundle bundle = new Bundle();
      bundle.putSerializable(FileConfig.FILE_CONFIG, config);
      fileDialog.setArguments(bundle);
      FragmentTransaction ft = getFragmentManager().beginTransaction();
      ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
      //在需要打开对话框时调用show函数
      fileDialog.show(ft, "fileDialog");
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

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

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
