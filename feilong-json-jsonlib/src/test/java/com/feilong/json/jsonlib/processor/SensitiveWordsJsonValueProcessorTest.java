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
package com.feilong.json.jsonlib.processor;

import static com.feilong.core.util.MapUtil.newHashMap;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.json.jsonlib.JavaToJsonConfig;
import com.feilong.json.jsonlib.JsonUtil;
import com.feilong.store.member.User;

import net.sf.json.processors.JsonValueProcessor;

/**
 * The Class SensitiveWordsJsonValueProcessorTest.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.8.5
 */
public class SensitiveWordsJsonValueProcessorTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveWordsJsonValueProcessorTest.class);

    /**
     * Test sensitive words json value processor 1.
     */
    @Test
    public void testSensitiveWordsJsonValueProcessor1(){
        User user = new User("feilong1", 24);
        user.setPassword("123456");

        Map<String, JsonValueProcessor> propertyNameAndJsonValueProcessorMap = newHashMap();
        propertyNameAndJsonValueProcessorMap.put("password", SensitiveWordsJsonValueProcessor.INSTANCE);
        propertyNameAndJsonValueProcessorMap.put("age", SensitiveWordsJsonValueProcessor.INSTANCE);

        JavaToJsonConfig jsonFormatConfig = new JavaToJsonConfig();
        jsonFormatConfig.setPropertyNameAndJsonValueProcessorMap(propertyNameAndJsonValueProcessorMap);
        jsonFormatConfig.setIncludes("name", "age", "password");

        LOGGER.debug(JsonUtil.format(user, jsonFormatConfig));
    }

}
