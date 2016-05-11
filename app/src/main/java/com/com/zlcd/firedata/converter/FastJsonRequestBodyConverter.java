package com.com.zlcd.firedata.converter;

/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @ Description:
 *
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @ Date 2016/5/11 12:29
 * @ Version V1.0
 */
final class FastJsonRequestBodyConverter<T>  implements Converter<T, RequestBody> {

  private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
  private SerializeConfig serializeConfig;
  private SerializerFeature[] serializerFeatures;

  FastJsonRequestBodyConverter(SerializeConfig config, SerializerFeature... features) {
    serializeConfig = config;
    serializerFeatures = features;
  }

  @Override
  public RequestBody convert(T value) throws IOException {
    byte[] content;
    if (serializeConfig != null) {
      if (serializerFeatures != null) {
        content = JSON.toJSONBytes(value, serializeConfig, serializerFeatures);
      } else {
        content = JSON.toJSONBytes(value, serializeConfig);
      }
    } else {
      if (serializerFeatures != null) {
        content = JSON.toJSONBytes(value, serializerFeatures);
      } else {
        content = JSON.toJSONBytes(value);
      }
    }
    return RequestBody.create(MEDIA_TYPE, content);
  }
}
