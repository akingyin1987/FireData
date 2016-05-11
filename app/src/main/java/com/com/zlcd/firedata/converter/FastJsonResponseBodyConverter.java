package com.com.zlcd.firedata.converter;

/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @ Description:
 *
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @ Date 2016/5/11 12:31
 * @ Version V1.0
 */
final class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
  private static final Feature[] EMPTY_SERIALIZER_FEATURES = new Feature[0];

  private Type mType;

  private ParserConfig config;
  private int featureValues;
  private Feature[] features;

  FastJsonResponseBodyConverter(Type type, ParserConfig config, int featureValues,
      Feature... features) {
    mType = type;
    this.config = config;
    this.featureValues = featureValues;
    this.features = features;
  }

  @Override
  public T convert(ResponseBody value) throws IOException {
    try {
      return JSON.parseObject(value.string(), mType, config, featureValues,
          features != null ? features : EMPTY_SERIALIZER_FEATURES);
    } finally {
      value.close();
    }
  }

}
