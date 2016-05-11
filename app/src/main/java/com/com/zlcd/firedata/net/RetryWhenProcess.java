package com.com.zlcd.firedata.net;

/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * @ Description:
 * 网络请求时自动重试//使用时：.retryWhen(new RetryWhenProcess(5))
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @ Date 2016/4/14 14:53
 * @ Version V1.0
 */
public class RetryWhenProcess  implements Func1<Observable<? extends Throwable>, Observable<?>>{
  private long mInterval;

  public RetryWhenProcess(long mInterval) {
    this.mInterval = mInterval;
  }

  @Override
  public Observable<?> call(Observable<? extends Throwable> observable) {
    return observable.flatMap(new Func1<Throwable, Observable<?>>() {
      @Override
      public Observable<?> call(Throwable throwable) {
        //未找开网络直接退出程序
        if (throwable instanceof UnknownHostException) {
          return Observable.error(throwable);
        }
        //最多重试5次
        return Observable.just(throwable).zipWith(Observable.range(1, 5), new Func2<Throwable, Integer, Integer>() {
          @Override
          public Integer call(Throwable throwable, Integer integer) {
            return integer;
          }
        }).flatMap(new Func1<Integer, Observable<? extends  Long>>() {
          @Override public Observable<? extends  Long> call(Integer retryCount) {
            return Observable.timer((long) Math.pow(mInterval, retryCount), TimeUnit.SECONDS);
          }
        });
      }
    });
  }
}
