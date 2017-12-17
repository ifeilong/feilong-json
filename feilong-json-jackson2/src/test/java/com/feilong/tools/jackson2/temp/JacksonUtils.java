package com.feilong.tools.jackson2.temp;

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
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * jsonson utils.
 *
 * @author magic_yy
 * @see http://jackson.codehaus.org/
 * @see https://github.com/FasterXML/jackson
 * @see http://wiki.fasterxml.com/JacksonHome
 */
public class JacksonUtils{

    /** The object mapper. */
    private static ObjectMapper objectMapper = new ObjectMapper();

    /** The xml mapper. */
    private static XmlMapper    xmlMapper    = new XmlMapper();

    /**
     * javaBean,list,array convert to json string.
     *
     * @param obj
     *            the obj
     * @return the string
     * @throws Exception
     *             the exception
     */
    public static String obj2json(Object obj) throws Exception{
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * json string convert to javaBean.
     *
     * @param <T>
     *            the generic type
     * @param jsonStr
     *            the json str
     * @param klass
     *            the klass
     * @return the t
     * @throws Exception
     *             the exception
     */
    public static <T> T json2pojo(String jsonStr,Class<T> klass) throws Exception{
        return objectMapper.readValue(jsonStr, klass);
    }

    /**
     * json string convert to map.
     *
     * @param <T>
     *            the generic type
     * @param jsonStr
     *            the json str
     * @return the map< string, object>
     * @throws Exception
     *             the exception
     */
    public static Map<String, Object> json2map(String jsonStr) throws Exception{
        return objectMapper.readValue(jsonStr, Map.class);
    }

    /**
     * json string convert to map with javaBean.
     *
     * @param <T>
     *            the generic type
     * @param jsonStr
     *            the json str
     * @param klass
     *            the klass
     * @return the map< string, t>
     * @throws Exception
     *             the exception
     */
    public static <T> Map<String, T> json2map(String jsonStr,Class<T> klass) throws Exception{
        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>(){});
        Map<String, T> result = new HashMap<>();
        for (Entry<String, Map<String, Object>> entry : map.entrySet()){
            result.put(entry.getKey(), map2pojo(entry.getValue(), klass));
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean.
     *
     * @param <T>
     *            the generic type
     * @param jsonArrayStr
     *            the json array str
     * @param klass
     *            the klass
     * @return the list< t>
     * @throws Exception
     *             the exception
     */
    public static <T> List<T> json2list(String jsonArrayStr,Class<T> klass) throws Exception{
        List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>(){});
        List<T> result = new ArrayList<>();
        for (Map<String, Object> map : list){
            result.add(map2pojo(map, klass));
        }
        return result;
    }

    /**
     * map convert to javaBean.
     *
     * @param <T>
     *            the generic type
     * @param map
     *            the map
     * @param klass
     *            the klass
     * @return the t
     */
    public static <T> T map2pojo(Map map,Class<T> klass){
        return objectMapper.convertValue(map, klass);
    }

    /**
     * json string convert to xml string.
     *
     * @param jsonStr
     *            the json str
     * @return the string
     * @throws Exception
     *             the exception
     */
    public static String json2xml(String jsonStr) throws Exception{
        JsonNode root = objectMapper.readTree(jsonStr);
        String xml = xmlMapper.writeValueAsString(root);
        return xml;
    }

    /**
     * xml string convert to json string.
     *
     * @param xml
     *            the xml
     * @return the string
     * @throws Exception
     *             the exception
     */
    public static String xml2json(String xml) throws Exception{
        StringWriter w = new StringWriter();
        JsonParser jp = xmlMapper.getFactory().createParser(xml);
        JsonGenerator jg = objectMapper.getFactory().createGenerator(w);
        while (jp.nextToken() != null){
            jg.copyCurrentEvent(jp);
        }
        jp.close();
        jg.close();
        return w.toString();
    }
}
