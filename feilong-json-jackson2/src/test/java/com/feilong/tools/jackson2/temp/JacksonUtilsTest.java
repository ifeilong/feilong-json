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
package com.feilong.tools.jackson2.temp;

import static com.feilong.core.util.CollectionsUtil.newArrayList;
import static com.feilong.core.util.MapUtil.newHashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class JacksonUtilsTest.
 */
public class JacksonUtilsTest{

    /** The Constant log. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtilsTest.class);

    /**
     * Test_pojo2json.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void test_pojo2json() throws Exception{
        String json = JacksonUtils.obj2json(new User(1, "张三"));
        assertEquals("{\"id\":1,\"name\":\"张三\"}", json);
        List<User> list = newArrayList();
        list.add(new User(1, "张三"));
        list.add(new User(2, "李四"));
        String json2 = JacksonUtils.obj2json(list);
        assertEquals("[{\"id\":1,\"name\":\"张三\"},{\"id\":2,\"name\":\"李四\"}]", json2);
        Map<String, User> map = newHashMap();
        map.put("user1", new User(1, "张三"));
        map.put("user2", new User(2, "李四"));
        String json3 = JacksonUtils.obj2json(map);
        assertEquals("{\"user2\":{\"id\":2,\"name\":\"李四\"},\"user1\":{\"id\":1,\"name\":\"张三\"}}", json3);
    }

    /**
     * Test_json2pojo.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void test_json2pojo() throws Exception{
        String json = "{\"id\":1,\"name\":\"张三\"}";
        User user = JacksonUtils.json2pojo(json, User.class);
        assertTrue(user.getId() == 1 && user.getName().equals("张三"));
    }

    /**
     * Test_json2pojo1.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void test_json2pojo1() throws Exception{
        String json = "{id:'1',name:'张三'}";
        Map map = new ObjectMapper().configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true).readValue("{a:\"1\", b:2}", HashMap.class);
        //Map map = new ObjectMapper().configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true).readValue(json, HashMap.class);
        LOGGER.debug("the param map:{}", map);

        //        User user = JacksonUtils.json2pojo(json, User.class);
        //        assertTrue(user.getId() == 1 && user.getName().equals("张三"));
    }

    /**
     * Test_json2map.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void test_json2map() throws Exception{
        String json = "{\"id\":1,\"name\":\"张三\"}";
        Map<String, Object> map = JacksonUtils.json2map(json);
        assertEquals("{id=1, name=张三}", map.toString());
        String json2 = "{\"user2\":{\"id\":2,\"name\":\"李四\"},\"user1\":{\"id\":1,\"name\":\"张三\"}}";
        Map<String, User> map2 = JacksonUtils.json2map(json2, User.class);
        User user1 = map2.get("user1");
        User user2 = map2.get("user2");
        assertTrue(user1.getId() == 1 && user1.getName().equals("张三"));
        assertTrue(user2.getId() == 2 && user2.getName().equals("李四"));
    }

    /**
     * Test_json2list.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void test_json2list() throws Exception{
        String json = "[{\"id\":1,\"name\":\"张三\"},{\"id\":2,\"name\":\"李四\"}]";
        List<User> list = JacksonUtils.json2list(json, User.class);
        User user1 = list.get(0);
        User user2 = list.get(1);
        assertTrue(user1.getId() == 1 && user1.getName().equals("张三"));
        assertTrue(user2.getId() == 2 && user2.getName().equals("李四"));
    }

    /**
     * Test_map2pojo.
     */
    @Test
    public void test_map2pojo(){
        Map<String, Object> map = newHashMap();
        map.put("id", 1);
        map.put("name", "张三");
        User user = JacksonUtils.map2pojo(map, User.class);
        assertTrue(user.getId() == 1 && user.getName().equals("张三"));
        System.out.println(user);
    }

    /**
     * Test_json2xml.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void test_json2xml() throws Exception{
        String json = "{\"id\":1,\"name\":\"张三\"}";
        String xml = JacksonUtils.json2xml(json);
        assertEquals("<ObjectNode xmlns=\"\"><id>1</id><name>张三</name></ObjectNode>", xml);
        String json2 = "{\"Items\":{\"RequestInterfaceSku\":[{\"Sku_ProductNo\":\"sku_0004\"},{\"Sku_ProductNo\":\"sku_0005\"}]}}";
        String xml2 = JacksonUtils.json2xml(json2);
        assertEquals(
                        "<ObjectNode xmlns=\"\"><Items><RequestInterfaceSku><Sku_ProductNo>sku_0004</Sku_ProductNo></RequestInterfaceSku><RequestInterfaceSku><Sku_ProductNo>sku_0005</Sku_ProductNo></RequestInterfaceSku></Items></ObjectNode>",
                        xml2);
    }

    /**
     * Test_xml2json.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void test_xml2json() throws Exception{
        String xml = "<ObjectNode xmlns=\"\"><id>1</id><name>张三</name></ObjectNode>";
        String json = JacksonUtils.xml2json(xml);
        assertEquals("{\"id\":1,\"name\":\"张三\"}", json);
        String xml2 = "<ObjectNode xmlns=\"\"><Items><RequestInterfaceSku><Sku_ProductNo>sku_0004</Sku_ProductNo></RequestInterfaceSku><RequestInterfaceSku><Sku_ProductNo>sku_0005</Sku_ProductNo></RequestInterfaceSku></Items></ObjectNode>";
        String json2 = JacksonUtils.xml2json(xml2);
        //expected2是我们想要的格式,但实际结果确实expected1,所以用jackson实现xml直接转换为json在遇到数组时是不可行的
        String expected1 = "{\"Items\":{\"RequestInterfaceSku\":{\"Sku_ProductNo\":\"sku_0004\"},\"RequestInterfaceSku\":{\"Sku_ProductNo\":\"sku_0005\"}}}";
        String expected2 = "{\"Items\":{\"RequestInterfaceSku\":[{\"Sku_ProductNo\":\"sku_0004\"},{\"Sku_ProductNo\":\"sku_0005\"}]}}";
        assertEquals(expected1, json2);
        assertEquals(expected2, json2);
    }

    /**
     * The Class User.
     */
    private static class User{

        /** The id. */
        private int    id;

        /** The name. */
        private String name;

        /**
         * The Constructor.
         */
        public User(){
        }

        /**
         * The Constructor.
         *
         * @param id
         *            the id
         * @param name
         *            the name
         */
        public User(int id, String name){
            this.id = id;
            this.name = name;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString(){
            return "{\"id\":" + id + ",\"name\":\"" + name + "\"}";
        }

        /**
         * 获得 id.
         *
         * @return the id
         */
        public int getId(){
            return id;
        }

        /**
         * 设置 id.
         *
         * @param id
         *            the id
         */
        public void setId(int id){
            this.id = id;
        }

        /**
         * 获得 name.
         *
         * @return the name
         */
        public String getName(){
            return name;
        }

        /**
         * 设置 name.
         *
         * @param name
         *            the name
         */
        public void setName(String name){
            this.name = name;
        }
    }
}
