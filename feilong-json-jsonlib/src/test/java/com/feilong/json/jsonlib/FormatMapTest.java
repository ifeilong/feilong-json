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

import java.util.Hashtable;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.json.AbstractJsonTest;

public class FormatMapTest extends AbstractJsonTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FormatMapTest.class);

    /**
     * Test hashtable.
     */
    @Test
    @SuppressWarnings("static-method")
    public void testHashtable(){
        Hashtable<String, Object> hashtable = new Hashtable<>();
        hashtable.put("a", "a");
        // hashtable.put("a", null);
        LOGGER.debug("hashtable:{}", JsonUtil.format(hashtable));
    }
}
