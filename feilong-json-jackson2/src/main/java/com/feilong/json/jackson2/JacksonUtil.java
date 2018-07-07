/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.json.jackson2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.feilong.core.UncheckedIOException;

/**
 * 基于 com.fasterxml.jackson.core 的 json 工具类.
 * 
 * <h3>依赖于下面的jar:</h3>
 * 
 * <blockquote>
 * 
 * <pre class="code">
 * {@code
 * <dependency>
 *     <groupId>com.fasterxml.jackson.core</groupId>
 *     <artifactId>jackson-databind</artifactId>
 * </dependency>
 * <dependency>
 *     <groupId>com.fasterxml.jackson.core</groupId>
 *     <artifactId>jackson-annotations</artifactId>
 * </dependency>
 * <dependency>
 *     <groupId>com.fasterxml.jackson.core</groupId>
 *     <artifactId>jackson-core</artifactId>
 * </dependency>
 * }
 * </pre>
 * 
 * </blockquote>
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @version 1.2.2 2015年7月8日 上午1:20:48
 * @since 1.2.2
 */
public class JacksonUtil{

    /** The Constant LOGGER. */
    private static final Logger       LOGGER = LoggerFactory.getLogger(JacksonUtil.class);

    /** The Constant objectMapper. */
    private static final ObjectMapper OBJECT_MAPPER;

    static{
        OBJECT_MAPPER = new ObjectMapper();
        //去掉默认的时间戳格式
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //设置为中国上海时区
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        //空值不序列化
        OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
        //反序列化时,属性不存在的兼容处理
        OBJECT_MAPPER.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //序列化时,日期的统一格式
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        OBJECT_MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
        OBJECT_MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //单引号处理
        OBJECT_MAPPER.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    //---------------------------------------------------------------

    /**
     * To json.
     *
     * @param <T>
     *            the generic type
     * @param entity
     *            the entity
     * @return the string
     */
    public static <T> String format(T entity){
        try{
            return OBJECT_MAPPER.writeValueAsString(entity);
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    //---------------------------------------------------------------

    public static <T> T toBean(String json,Class<T> klass){
        try{
            return OBJECT_MAPPER.readValue(json, klass);
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    /**
     * To collection.
     *
     * @param <T>
     *            the generic type
     * @param json
     *            the json
     * @param typeReference
     *            the type reference
     * @return the t
     */
    public static <T> T toList(String json,TypeReference<T> typeReference){
        try{
            return OBJECT_MAPPER.readValue(json, typeReference);
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }
}
