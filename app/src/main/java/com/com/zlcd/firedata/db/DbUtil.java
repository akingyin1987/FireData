package com.com.zlcd.firedata.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/8.
 */
public class DbUtil {

    public   static List<Cbc>   findAllCbc(SQLiteDatabase   db){
        List<Cbc>    datas = new ArrayList<>();
        Cursor  cursor = db.rawQuery(CbcTable.query_all_sql,null);
        while (cursor.moveToNext()){
            Cbc   cbc = new Cbc();
            cbc.jhcbrq = cursor.getString(cursor.getColumnIndex("jhcbrq"));
            cbc.cbccsr = cursor.getString(cursor.getColumnIndex("cbccsr"));
            cbc.cbccssj = cursor.getString(cursor.getColumnIndex("cbccssj"));
            cbc.cbcid = cursor.getString(cursor.getColumnIndex("cbcid"));
            cbc.cbgxm = cursor.getString(cursor.getColumnIndex("cbgxm"));
            cbc.cbjhid = cursor.getString(cursor.getColumnIndex("cbjhid"));
            cbc.cbqj = cursor.getString(cursor.getColumnIndex("cbqj"));
            cbc.jlts = cursor.getInt(cursor.getColumnIndex("jlts"));
            cbc.xzsj = cursor.getString(cursor.getColumnIndex("xzsj"));
            cbc.zxzt = cursor.getInt(cursor.getColumnIndex("zxzt"));
            cbc.cbjhms=cursor.getString(cursor.getColumnIndex("cbjhms"));
            cbc.cbgid = cursor.getString(cursor.getColumnIndex("cbgid"));
            datas.add(cbc);
        }
        cursor.close();
        return  datas;
    }


    public   boolean   addCbc(Cbc  cbc,SQLiteDatabase  db){
        try {
            ContentValues     values = new ContentValues();
            values.put("jhcbrq",cbc.jhcbrq);
            values.put("cbccsr",cbc.cbccsr);
            values.put("cbccssj",cbc.cbccssj);
            values.put("cbcid",cbc.cbcid);
            values.put("cbgxm",cbc.cbgxm);
            values.put("cbjhid",cbc.cbjhid);
            values.put("cbqj",cbc.cbqj);
            values.put("jlts",cbc.jlts);
            values.put("xzsj",cbc.xzsj);
            values.put("cbjhms",cbc.cbjhms);
            values.put("cbgid", cbc.cbgid);
            db.insert(CbcTable.table_name,null,values);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  false;
    }

    public   boolean   updateCbc(Cbc   cbc,SQLiteDatabase  db){
        try {
            ContentValues     values = new ContentValues();
            values.put("jhcbrq",cbc.jhcbrq);
            values.put("cbccsr",cbc.cbccsr);
            values.put("cbccssj",cbc.cbccssj);
            values.put("cbcid",cbc.cbcid);
            values.put("cbgxm",cbc.cbgxm);
            values.put("cbjhid",cbc.cbjhid);
            values.put("cbqj",cbc.cbqj);
            values.put("jlts",cbc.jlts);
            values.put("xzsj",cbc.xzsj);
            values.put("cbjhms",cbc.cbjhms);
            values.put("cbgid", cbc.cbgid);
            db.update(CbcTable.table_name,values,"cbcid=?",new String[]{cbc.cbcid});
            return  true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  false;
    }
}
