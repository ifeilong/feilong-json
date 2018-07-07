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
package com.feilong.tools.jackson2;

import static com.feilong.core.util.MapUtil.newHashMap;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.feilong.core.UncheckedIOException;
import com.feilong.core.bean.BeanUtil;
import com.feilong.json.AbstractJsonTest;
import com.feilong.json.HttpMethodTestType;
import com.feilong.json.jackson2.JacksonUtil;
import com.feilong.json.jsonlib.JsonUtil;
import com.feilong.store.member.Person;
import com.feilong.store.member.User;

/**
 * The Class JacksonUtilTest.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @version 1.2.2 2015年7月8日 上午1:26:34
 * @since 1.2.2
 */
public class JacksonUtilTest extends AbstractJsonTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtilTest.class);

    @Test
    public void toMap(){
        LOGGER.debug(JsonUtil.format(JacksonUtil.toBean("{'data1':{'name':'get'},'data2':{'name':'set'}}", Person.class)));
        LOGGER.debug(JsonUtil.format(JacksonUtil.toBean("{'data1':{'name':'get'},'data2':{'name':'set'}}", null)));
    }

    @Test
    public void testToJson(){
        LOGGER.debug("{}", writeValueAsString(USER));

        String json = "{'name':'get','dateAttr':'2009-11-12'}";
        LOGGER.debug(writeValueAsString(json));
    }

    @Test
    public void testToJson1(){
        LOGGER.debug(JacksonUtil.format(USER));
    }

    @Test
    public void testToJson2(){
        LOGGER.debug(JacksonUtil.format(HttpMethodTestType.GET));
    }

    @Test
    public void testBasicDynaClass(){
        //        Map<String, Class<?>> typeMap = new HashMap<>();
        //        typeMap.put("address", java.util.Map.class);
        //        typeMap.put("firstName", String.class);
        //        typeMap.put("lastName", String.class);

        Map<String, Object> valueMap = newHashMap();
        valueMap.put("address", new HashMap());
        valueMap.put("firstName", "Fred");
        valueMap.put("lastName", "Flintstone");

        DynaBean dynaBean = BeanUtil.newDynaBean(valueMap);
        LOGGER.debug(JacksonUtil.format(dynaBean));
    }

    @Test
    public void testToJson3(){
        JacksonUtil.toBean("[1,12]", Integer.class);
    }

    @Test
    public void testToBean(){
        User user = JacksonUtil.toBean(USER_JSON_STRING, User.class);
        LOGGER.debug("{}", user);
    }

    /**
     * Write value as string.
     *
     * @param value
     *            the value
     * @return the string
     * @since 1.2.2
     */
    public static String writeValueAsString(Object value){
        ObjectMapper objectMapper = new ObjectMapper();
        // 仅输出一行json字符串
        //objectMapper.writeValue(System.out, value);

        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        // 将字符串美化成多行
        //ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        try{
            return objectMapper.writeValueAsString(value);
        }catch (JsonProcessingException e){
            throw new UncheckedIOException(e);
        }
    }
}
