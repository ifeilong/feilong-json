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

import static com.feilong.core.bean.ConvertUtil.toArray;
import static com.feilong.core.bean.ConvertUtil.toBigDecimal;
import static com.feilong.core.bean.ConvertUtil.toList;
import static com.feilong.core.bean.ConvertUtil.toMap;
import static com.feilong.core.date.DateUtil.now;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.feilong.store.member.User;
import com.feilong.store.member.UserAddress;
import com.feilong.store.member.UserInfo;

/**
 * The Class FastjsonUtilTest.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @version 1.2.2 2015年7月11日 上午4:27:57
 * @since 1.2.2
 */
public class FormatTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FormatTest.class);

    /** The Constant USER. */
    protected static final User USER   = getUserForJsonTest();

    /**
     * TestFastjsonUtil.
     */
    @Test
    public void testFastjsonUtil(){//SerializationFeature
        LOGGER.debug(JSON.toJSONString(USER));
    }

    @Test
    public void testFastjsonUtilMap(){
        Map<String, Object> map = toMap("name", (Object) "zhangfei", "age", 20);
        map.put("love", null);
        map.put("age0", null);

        LOGGER.debug(FastJsonUtil.format(map));
    }

    @Test
    public void testFastjsonUtil1(){//SerializationFeature
        LOGGER.debug(FastJsonUtil.format(USER));

        String json = "{'name':'get','dateAttr':'2009-11-12'}";
        LOGGER.debug(FastJsonUtil.format(json));
    }

    /**
     * Gets the json string.
     * 
     * @return the json string
     */
    private static User getUserForJsonTest(){
        User user = new User();

        user.setPassword("123456");
        user.setId(8L);
        user.setName("feilong");
        user.setDate(now());
        user.setMoney(toBigDecimal(null));

        user.setLoves(toArray("桔子", "香蕉"));
        user.setUserInfo(new UserInfo(10));

        UserAddress userAddress1 = new UserAddress("上海市闸北区万荣路1188号H座109-118室");
        UserAddress userAddress2 = new UserAddress("上海市闸北区阳城路280弄25号802室(阳城贵都)");

        user.setUserAddresses(toArray(userAddress1, userAddress2));
        user.setUserAddresseList(toList(userAddress1, userAddress2));

        return user;
    }
}
