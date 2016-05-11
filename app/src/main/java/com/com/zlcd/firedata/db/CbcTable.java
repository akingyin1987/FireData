package com.com.zlcd.firedata.db;

/**
 * Created by Administrator on 2016/5/8.
 */
public class CbcTable  {

    public   static   String   table_name="cbc_table";

    public   static   String   Columns_CBCID="cbcid";

    public   static   String   Columns_cbjhid="cbjhid";


    public   static   String   query_all_sql="select * from "+table_name;

    public   static   String    table_create_sql="create table if not exists  "+table_name+"  ( " +
            "    cbcid   VARCHAR( 20 )," +
            "    cbjhid  VARCHAR( 20 )," +
            "    cbjhms  VARCHAR( 200 )," +
            "    cbqj    VARCHAR( 10 )," +
            "    cbccsr  VARCHAR( 44 )," +
            "    cbccssj DATETIME," +
            "    cbgid   VARCHAR( 10 )," +
            "    cbgxm   VARCHAR( 44 )," +
            "    jhcbrq  DATE," +
            "    xzsj    DATATIME," +
            "    jlts    INTEGER         DEFAULT '0'," +
            "    zxzt    INTEGER         DEFAULT '0' " +
            ")";
}
