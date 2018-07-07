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

import static com.feilong.core.bean.ConvertUtil.toArray;
import static com.feilong.core.bean.ConvertUtil.toList;
import static com.feilong.core.bean.ConvertUtil.toSet;
import static com.feilong.core.util.CollectionsUtil.newArrayList;
import static com.feilong.core.util.MapUtil.newHashMap;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.bean.PropertyUtil;
import com.feilong.json.AbstractJsonTest;
import com.feilong.json.HttpMethodTestType;
import com.feilong.json.jsonlib.entity.MyBean;
import com.feilong.store.member.Person;
import com.feilong.store.member.User;
import com.feilong.store.member.UserInfo;
import com.feilong.store.system.Menu;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class FormatTest extends AbstractJsonTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FormatTest.class);

    /**
     * Test json menu.
     */
    @Test
    @SuppressWarnings("static-method")
    public void testJsonMenu(){
        Menu menu = new Menu(4L);
        menu.setChildren(toList(new Menu(5L)));

        LOGGER.debug(JsonUtil.format(menu));
    }

    /**
     * Test json string.
     */
    @Test
    @SuppressWarnings("static-method")
    public void testJsonString(){
        LOGGER.debug("{}--->{}", USER_JSON_STRING, JsonUtil.format(USER_JSON_STRING));
    }

    /**
     * Name.
     */
    @Test
    @SuppressWarnings("static-method")
    public void name(){
        String json_test = "{name=\"json\",bool:true,int:1,double:2.2,func:function(a){ return a; },array:[1,2]}";

        JSONObject jsonObject = JSONObject.fromObject(json_test);
        Object bean = JSONObject.toBean(jsonObject);

        assertEquals(jsonObject.get("name"), PropertyUtil.getProperty(bean, "name"));
        assertEquals(jsonObject.get("bool"), PropertyUtil.getProperty(bean, "bool"));
        assertEquals(jsonObject.get("int"), PropertyUtil.getProperty(bean, "int"));
        assertEquals(jsonObject.get("double"), PropertyUtil.getProperty(bean, "double"));
        assertEquals(jsonObject.get("func"), PropertyUtil.getProperty(bean, "func"));
        List<?> expected = JSONArray.toList(jsonObject.getJSONArray("array"));
        assertEquals(expected, PropertyUtil.getProperty(bean, "array"));
    }

    /**
     * TestJsonUtilTest.
     */
    @Test
    @SuppressWarnings("static-method")
    public void testFormatWithIncludes1(){
        User user1 = new User("feilong1", 24);
        user1.setNickNames(toArray("xin.jin", "shuai.ge"));
        User user2 = new User("feilong2", 240);
        user2.setNickNames(toArray("xin.jin", "shuai.ge"));

        List<User> list = toList(user1, user2);

        LOGGER.debug(JsonUtil.formatWithIncludes(list, "name", "age"));

        String[] array = { "id", "name" };
        LOGGER.debug(JsonUtil.formatWithIncludes(toArray(user1, user2), array));

        LOGGER.debug(JsonUtil.formatWithIncludes(toList("2,5,8", "2,5,9")));
        LOGGER.debug(JsonUtil.formatWithIncludes(user1));
    }

    /**
     * TestJsonUtilTest.
     */
    @Test
    @SuppressWarnings("static-method")
    public void testFormatWithIncludes(){
        Object[][] objects = { { "nike shoe", "500", 1 }, { "nike shoe2", "5000", 1 } };
        LOGGER.debug(JsonUtil.formatWithIncludes(objects));
    }

    /**
     * To bean n ull.
     */
    @Test
    @SuppressWarnings("static-method")
    public void toBeanNUll(){
        LOGGER.debug(toJSON(null).toString(4, 4));
        LOGGER.debug(new JSONObject().toString(4));
    }

    /**
     * To json.
     */
    @SuppressWarnings("static-method")
    @Test
    public void toJSON(){
        LOGGER.debug(toJSON(HttpMethodTestType.GET).toString(4, 4));
    }

    /**
     * To bean n ulluser.
     */
    @SuppressWarnings("static-method")
    @Test
    public void toBeanNUlluser(){
        User user = new User();
        user.setId(8L);
        user.setName("feilong");

        JsonConfig jsonConfig = new JsonConfig();

        // String[] excludes = { "userInfo" };
        // jsonConfig.setExcludes(excludes);

        Class<UserInfo> target = UserInfo.class;
        String[] properties = { "age" };
        jsonConfig.registerPropertyExclusions(target, properties);
        LOGGER.debug(JsonHelper.toJSON(user, jsonConfig).toString(4, 4));
    }

    /**
     * Name.
     */
    @SuppressWarnings("static-method")
    @Test
    public void name1(){
        Map<String, Object> map = newHashMap();

        Map<String, Object> map1 = newHashMap();

        String[] aStrings = { "aaaa", "bbbb" };
        map1.put("b", aStrings);
        map1.put("bb", "2");
        map1.put("bbb", "3");

        map.put("a", map1);
        map.put("aa", map1);
        map.put("aaa", map1);
        LOGGER.debug(toJSON(map).toString(4, 4));
    }

    /**
     * 实体Bean转json串 void.
     */
    @Test
    @SuppressWarnings("static-method")
    public void testgetJsonStr1(){
        Person ps = new Person();
        ps.setDateAttr(new Date());
        ps.setName("get");
        MyBean myBean = new MyBean();
        List<Object> list = newArrayList();
        list.add(ps);

        myBean.setData(list);
        // print: {"data":[{"dateAttr":"2009-09-12 07:24:54","name":"get"}]}
        LOGGER.debug("" + toJSON(myBean));
    }

    /**
     * list转json串 void.
     */
    @Test
    @SuppressWarnings("static-method")
    public void testgetJsonStr4(){
        Person ps = new Person();
        ps.setDateAttr(new Date());
        ps.setName("get");
        List<Person> list = newArrayList();
        list.add(ps);

        // print: [{"dateAttr":"2009-09-12 07:22:49","name":"get"}]
        LOGGER.debug("" + toJSON(list));

        Set set = toSet(ps);

        // print: [{"dateAttr":"2009-09-12 07:22:16","name":"get"}]
        LOGGER.debug("" + toJSON(set));

        Person[] personArr = new Person[1];
        personArr[0] = ps;
        // print: [{"dateAttr":"2009-09-12 07:23:54","name":"get"}]
        LOGGER.debug("" + toJSON(personArr));

        Map map = new LinkedHashMap();
        map.put("person1", ps);

        // print: {"person1":{"dateAttr":"2009-09-12 07:24:27","name":"get"}}
        LOGGER.debug("" + toJSON(map));
    }

    /**
     * 把实体Bean、Map对象、数组、列表集合转换成Json串.
     *
     * @param obj
     *            the obj
     * @return the jSON
     * @see #toJSON(Object, JsonConfig)
     */
    static JSON toJSON(Object obj){
        return JsonHelper.toJSON(obj, null);
    }
}
