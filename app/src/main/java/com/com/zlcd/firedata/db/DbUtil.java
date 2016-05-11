package com.com.zlcd.firedata.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Base64;
import java.net.URLDecoder;
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


    public static   boolean   addCbc(Cbc  cbc,SQLiteDatabase  db){
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

    public static   boolean   updateCbc(Cbc   cbc,SQLiteDatabase  db){
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

    public   static   String    Base64Edecode(String   str){
        try {
           return  str;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public   static   String    getStringByColumnName(Cursor cursor,String  column){
        return cursor.getString(cursor.getColumnIndex(column));
    }
    public   static   Integer    getIntegerByColumnName(Cursor cursor,String  column){
        return cursor.getInt(cursor.getColumnIndex(column));
    }
    public   static   List<Cbjl>   findAllCbjlByCbcId(String  cbcid,SQLiteDatabase  db){
        List<Cbjl>   items = new ArrayList<>();
        Cursor  cursor = db.rawQuery(CbjlTable.query_all_in_cbc_sql,new String[]{cbcid});
        while (cursor.moveToNext()){
            Cbjl   cbjl  = new Cbjl();
            cbjl.cbcid = cursor.getString(cursor.getColumnIndex(CbjlTable.Columns_CBCID));
            cbjl.cbjlid = getStringByColumnName(cursor,CbjlTable.Columns_CBJLID);
            cbjl.yhmc = Base64Edecode(getStringByColumnName(cursor,"yhmc"));
            cbjl.yqdz = Base64Edecode(getStringByColumnName(cursor,"yqdz"));
            cbjl.bjms = getStringByColumnName(cursor,"bjms");
            cbjl.bjgh = getStringByColumnName(cursor,"bjgh");
            cbjl.lc = getStringByColumnName(cursor,"lc");
            cbjl.pjyql = getStringByColumnName(cursor,"pjyql");
            cbjl.scbd = getStringByColumnName(cursor,"scbd");
            cbjl.jg1=getStringByColumnName(cursor,"jg1");
            cbjl.jg2=getStringByColumnName(cursor,"jg2");
            cbjl.zhye = getStringByColumnName(cursor,"zhye");
            cbjl.cbqk = getIntegerByColumnName(cursor,"cbqk");
            cbjl.zxzt = getIntegerByColumnName(cursor,"zxzt");
            cbjl.zhl_fangshi = getIntegerByColumnName(cursor,"zhl_fangshi");
            cbjl.isshufei = getIntegerByColumnName(cursor,"isshufei");
            cbjl.wenyabuchangxishu = getStringByColumnName(cursor,"wenyabuchangxishu");

            items.add(cbjl);
        }
        cursor.close();
        return  items;
    }

    public   static   boolean   updateCbjl(Cbjl cbjl,String   cbcid,String  cbjlid,SQLiteDatabase db){
        try {
            ContentValues   values  = new ContentValues();
            values.put("bjms",cbjl.bjms);
            db.update(CbjlTable.table_name,values,
                CbjlTable.Columns_CBCID+"=? and "+CbjlTable.Columns_CBJLID+"=?",
                new String[]{cbcid,cbjlid});
            return  true;
        }catch (Exception e){
            e.printStackTrace();

        }
        return  false;
    }
}
