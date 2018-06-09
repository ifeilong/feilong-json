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
package com.feilong.json.jsonlib;

import static com.feilong.core.util.MapUtil.newHashMap;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.10.5
 */
public class Temp{

    private static final Logger LOGGER = LoggerFactory.getLogger(Temp.class);

    /**
     * Test 3.
     */
    @Test
    @SuppressWarnings("static-method")
    public void test3(){
        Map<String, String> map = newHashMap();
        map.put("1", "2");

        String format = JsonUtil.format(map);

        Map<String, Integer> map2 = JsonUtil.toMap(format);

        for (Map.Entry<String, Integer> entry : map2.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ":" + value);//TODO:remove
        }
    }
}
