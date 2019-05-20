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

import static com.feilong.core.bean.ConvertUtil.toMap;

import java.util.Map;

import org.junit.Test;

import com.feilong.json.AbstractJsonTest;

public class FormatJsonMapTest extends AbstractJsonTest{

    /**
     * Test json string 11.
     */
    @Test
    @SuppressWarnings("static-method")
    public void testJsonString11(){
        Map<String, Object> map = toMap("ID", (Object) 4616189619433466044L);
        LOGGER.debug("{}", JsonUtil.format(map));
    }

    /**
     * Test json string2.
     */
    @Test
    @SuppressWarnings("static-method")
    public void testJsonMap(){
        Map<String, String> nullMap = null;
        LOGGER.debug(JsonUtil.format(nullMap));
    }

}
