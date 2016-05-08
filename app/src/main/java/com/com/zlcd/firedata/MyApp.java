package com.com.zlcd.firedata;
import android.app.Application;
import com.com.zlcd.firedata.db.DatabaseContext;
import com.com.zlcd.firedata.db.DatabaseHelper;
import org.xutils.DbManager;

/**
 * Created by Administrator on 2016/5/8.
 */
public class MyApp extends Application {

    private    static    MyApp    app;

    private  DbManager.DaoConfig  daoConfig;

    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }

    public void setDaoConfig(DbManager.DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

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
