package com.com.zlcd.firedata.db;

/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/**
 * @ Description:
 *
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @ Date 2016/5/10 17:11
 * @ Version V1.0
 */
public class CbjlTable {

  public   static   String   table_name="cbjl_table";

  public   static   String   Columns_CBCID="cbcid";

  public   static   String   Columns_CBJLID="cbjlid";


  public   static   String   query_all_sql="select * from "+table_name;

  public   static   String   query_all_in_cbc_sql="select * from "+ table_name+" where "+Columns_CBCID+"=?";

  public   static   String    table_create_sql="create table if not exists "+table_name+"( \n"
      + "    cbcid             VARCHAR( 20 ),"
      + "    cbjlid            VARCHAR( 20 ),"
      + "    yhh               VARCHAR( 20 ),"
      + "    cbcx              VARCHAR( 5 ),"
      + "    yhmc              VARCHAR( 100 ),"
      + "    yqdz              VARCHAR( 200 ),"
      + "    lxdh              VARCHAR( 50 ),"
      + "    bjms              VARCHAR( 20 ),"
      + "    bjgh              VARCHAR( 30 ),"
      + "    lc                VARCHAR( 10 ),"
      + "    pjyql             VARCHAR( 20 ),"
      + "    tjyz              VARCHAR( 20 ),"
      + "    scbd              VARCHAR( 20 ),"
      + "    djfs              VARCHAR( 2 ),"
      + "    jg1               VARCHAR( 200 ),"
      + "    jg2               VARCHAR( 200 ),"
      + "    hybl              VARCHAR( 20 ),"
      + "    zhye              VARCHAR( 20 ),"
      + "    qfhj              VARCHAR( 100 ),"
      + "    yhdzbm            VARCHAR( 200 ),"
      + "    bcbd              VARCHAR( 20 ),"
      + "    cbqk              INTEGER         DEFAULT '-1',"
      + "    cbsj              DATATIME,"
      + "    zxzt              INTEGER         DEFAULT '0',"
      + "    bcql              VARCHAR( 20 ),"
      + "    bcqf              VARCHAR( 20 ),"
      + "    qybds             VARCHAR( 20 ),"
      + "    ordertime         VARCHAR( 20 ),"
      + "    orderamount       VARCHAR( 20 ),"
      + "    ljgql             VARCHAR( 20 ),"
      + "    rqblx             VARCHAR( 5 ),"
      + "    yyql              VARCHAR( 20 ),"
      + "    syql              VARCHAR( 20 ),"
      + "    kzql              VARCHAR( 20 ),"
      + "    bh_qk             VARCHAR( 50 ),"
      + "    ygcl              VARCHAR( 20 ),"
      + "    tjql              VARCHAR( 20 ),"
      + "    zyql              VARCHAR( 20 ),"
      + "    jbqyds            VARCHAR( 20 ),"
      + "    jbscbds           VARCHAR( 20 ),"
      + "    jbbcds            VARCHAR( 20 ),"
      + "    shj_feiyong       VARCHAR( 20 ),"
      + "    shshjiner         VARCHAR( 20 ),"
      + "    zhaoling          VARCHAR( 20 ),"
      + "    chrzhh            VARCHAR( 20 ),"
      + "    zhl_fangshi       INTEGER         DEFAULT '0',"
      + "    isshufei          INTEGER         DEFAULT '0',"
      + "    qianfeimiaoshu    VARCHAR( 150 ),"
      + "    wenyabuchangxishu VARCHAR( 30 ),"
      + "    sccbsj            VARCHAR( 30 ),"
      + "    scyql             VARCHAR( 30 ),"
      + "    sccbqk            VARCHAR( 30 ),"
      + "    qcbd              VARCHAR( 30 ),"
      + "    qccbqk            VARCHAR( 30 ),"
      + "    tqrq              VARCHAR( 30 ),"
      + "    conSxrq           VARCHAR( 30 ) "
      + ")";
}
