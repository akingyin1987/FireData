package com.com.zlcd.firedata.net.api;

/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import com.com.zlcd.firedata.db.Cbc;
import okhttp3.ResponseBody;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observer;

/**
 * @ Description:
 *
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @ Date 2016/5/11 13:55
 * @ Version V1.0
 */
public interface GasDataApi {

     //上传提交表册信息
     @POST
     Observer<ResponseBody>   submitMeterList(@Body Cbc cbc);


     @POST
     Observer<ResponseBody>   getMeterData(@Body String  cbcid);
}
