package com.com.zlcd.firedata.net;

/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import com.com.zlcd.firedata.converter.FastJsonConverterFactory;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @ Description:
 *
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @ Date 2016/5/11 12:40
 * @ Version V1.0
 */
public class RetrofitUtils {

  private static Retrofit singleton;



  private  static  final  String   baseUrl="https://api.douban.com";

  public static final String BAIDU_IMAGES_URLS = "http://image.baidu.com";

  public static <T> T createApi(Class<T> clazz) {
    if (singleton == null) {
      synchronized (RetrofitUtils.class) {
        if (singleton == null) {
          Retrofit.Builder builder = new Retrofit.Builder();
          builder.baseUrl(baseUrl);//设置远程地址
          builder.addConverterFactory(FastJsonConverterFactory.create());
          builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
          builder.client(OkHttpUtils.getInstance());
          singleton = builder.build();
        }
      }
    }
    return singleton.create(clazz);
  }


  public static <T> T createApi(Class<T> clazz,String baseUrl,
      Converter.Factory  converFactory, CallAdapter.Factory  callFactory) {
    synchronized (RetrofitUtils.class) {
      Retrofit.Builder builder = new Retrofit.Builder();
      builder.baseUrl(baseUrl);//设置远程地址

      builder.client(OkHttpUtils.getInstance());
      if(null != converFactory){
        builder.addConverterFactory(converFactory);
      }
      if(null != callFactory){
        builder.addCallAdapterFactory(callFactory);
      }
      return builder.build().create(clazz);
    }
  }

}
