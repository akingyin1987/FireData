package com.com.zlcd.firedata.activity;

/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @ Description:
 *
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @ Date 2016/5/11 14:37
 * @ Version V1.0
 */
public class SharedpreferencesUtil {

   private    Context   context;

   private SharedPreferences   preferences;

   public   static   final  String    NAME="setting_info";

   public    SharedpreferencesUtil(Context  context){
     this.context = context;
     preferences = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);

   }

   public   void    saveData(String  key,String  value){
     preferences.edit().putString(key,value).apply();
   }

   public   void    saveData(String  key,int  value){
     preferences.edit().putInt(key,value).apply();
   }

   public   void    saveData(String key,long   value){
     preferences.edit().putLong(key,value).apply();
   }

   public   void    cleanData(String  key){
     preferences.edit().remove(key).apply();
   }

   public   String    getStringData(String  key){
     return  preferences.getString(key,"");
   }

   public   int      getIntData(String  key){
     return  preferences.getInt(key,0);
   }
}
