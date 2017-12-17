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

import static com.feilong.core.bean.ConvertUtil.toBigDecimal;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.store.member.User;

/**
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.10.5
 */
public class ToBeanTest{

    private static final Logger LOGGER = LoggerFactory.getLogger(ToBeanTest.class);

    @Test
    public void testToBean(){
        User user = FastJsonUtil.toBean("{'password':'123456','name':'feilong','money':'99999999.00','loves':['桔子', '香蕉']}", User.class);
        user.setId(10L);

        assertThat(user, allOf(//
                        hasProperty("id", is(10L)),
                        hasProperty("password", is("123456")),
                        hasProperty("name", is("feilong")),
                        hasProperty("money", is(toBigDecimal("99999999.00"))),
                        hasProperty("loves", arrayContaining("桔子", "香蕉"))
        //  
        ));
    }

    @Test
    public void toMap12(){
        Map<String, String> map = FastJsonUtil.toMap("{'brandCode':'UA'}");
        assertThat(map, allOf(hasEntry("brandCode", "UA")));

        Map<String, Integer> map2 = FastJsonUtil.toMap("{'brandCode':55555}");
        assertThat(map2, allOf(hasEntry("brandCode", 55555)));
    }

}
