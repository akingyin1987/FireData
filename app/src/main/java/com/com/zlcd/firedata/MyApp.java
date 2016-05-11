package com.com.zlcd.firedata;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;
import com.com.zlcd.firedata.activity.SharedpreferencesUtil;
import com.com.zlcd.firedata.db.DatabaseContext;
import com.com.zlcd.firedata.db.DatabaseHelper;
import com.facebook.stetho.Stetho;
import java.io.File;

/**
 * Created by Administrator on 2016/5/8.
 */
public class MyApp extends Application {

    private    static    MyApp    app;

    private SQLiteDatabase    db;

    public SQLiteDatabase getDb() {
        return db;
    }


    public   static MyApp  getIntense(){
        return  app;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(Stetho.newInitializerBuilder(this).
            enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build());
        app = this;
        initSqliteDb();
    }

    public   void   initSqliteDb(){
        if(null != db){
            db.close();
        }
        SharedpreferencesUtil   pref = new SharedpreferencesUtil(this);
        String   dbpath = pref.getStringData("dbpath");
        if(TextUtils.isEmpty(dbpath)){
            dbpath = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"gas_database";
            pref.saveData("dbpath",dbpath);
        }
        DatabaseContext   databaseContext = new DatabaseContext(this,dbpath);
        DatabaseHelper    databaseHelper = new DatabaseHelper(databaseContext);
        db = databaseHelper.getWritableDatabase();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if(null != db){
            db.close();
        }
    }
}
