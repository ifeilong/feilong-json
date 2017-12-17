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
package com.feilong.json.fastjson;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.feilong.core.bean.BeanUtil;

/**
 * The Class FastjsonUtilTest.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @version 1.2.2 2015年7月11日 上午4:27:57
 * @since 1.2.2
 */
public class FastjsonUtilTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FastjsonUtilTest.class);

    /**
     * TestFastjsonUtilTest.
     */
    @Test
    public void testFastjsonUtilTest(){
        Map<String, String> map = new HashMap<>();
        map.put(1 + "", 10 + "");
        String jsonStr = JSON.toJSONString(map);
        Map<Long, Integer> map2 = JSON.parseObject(jsonStr, new TypeReference<Map<Long, Integer>>(){});

        for (Map.Entry<Long, Integer> entry : map2.entrySet()){
            Long key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ":" + value);//TODO:remove
        }
        System.out.println(JSON.toJSONString(map2));
        System.out.println(map2.get(1L));
    }

    @Test
    public void testBasicDynaClass(){
        //        Map<String, Class<?>> typeMap = new HashMap<>();
        //        typeMap.put("address", java.util.Map.class);
        //        typeMap.put("firstName", String.class);
        //        typeMap.put("lastName", String.class);

        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("address", new HashMap());
        valueMap.put("firstName", "Fred");
        valueMap.put("lastName", "Flintstone");

        DynaBean dynaBean = BeanUtil.newDynaBean(valueMap);
        LOGGER.debug(JSON.toJSONString(dynaBean, SerializerFeature.PrettyFormat));
    }

}
