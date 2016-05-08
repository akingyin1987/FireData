package com.com.zlcd.firedata;
import android.app.Application;
import com.com.zlcd.firedata.db.DatabaseContext;
import com.com.zlcd.firedata.db.DatabaseHelper;


/**
 * Created by Administrator on 2016/5/8.
 */
public class MyApp extends Application {

    private    static    MyApp    app;


    public   static MyApp  getIntense(){
        return  app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        DatabaseContext   databaseContext = new DatabaseContext(this);
        DatabaseHelper    databaseHelper = new DatabaseHelper(databaseContext);
        databaseHelper.getWritableDatabase();
    }
}
