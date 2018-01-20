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
package com.feilong.json.jsonlib.builder;

import static com.feilong.core.Validator.isNotNullOrEmpty;

import java.util.Date;
import java.util.Map;

import com.feilong.core.DatePattern;
import com.feilong.json.jsonlib.JavaToJsonConfig;
import com.feilong.json.jsonlib.processor.DateJsonValueProcessor;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.processors.PropertyNameProcessor;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * The Class JsonConfigBuilder.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.10.3
 */
public final class JsonConfigBuilder{

    /** The Constant DEFAULT_JAVA_TO_JSON_CONFIG. */
    public static final JsonConfig DEFAULT_JAVA_TO_JSON_CONFIG = buildDefaultJavaToJsonConfig();

    //---------------------------------------------------------------

    /** Don't let anyone instantiate this class. */
    private JsonConfigBuilder(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    //---------------------------------------------------------------

    /**
     * Builds the.
     *
     * @param obj
     *            the obj
     * @param javaToJsonConfig
     *            the java to json config
     * @return the json config
     */
    public static JsonConfig build(@SuppressWarnings("unused") Object obj,JavaToJsonConfig javaToJsonConfig){
        if (null == javaToJsonConfig){
            return null;
        }

        //-----------------------------------------------------------------

        JsonConfig jsonConfig = buildDefaultJavaToJsonConfig();

        //---------------------------------------------------------------

        registerJsonPropertyNameProcessor(javaToJsonConfig, jsonConfig);

        //value处理器
        registerJsonValueProcessor(javaToJsonConfig, jsonConfig);

        //---------------------------------------------------------------

        //排除
        if (isNotNullOrEmpty(javaToJsonConfig.getExcludes())){
            jsonConfig.setExcludes(javaToJsonConfig.getExcludes());
        }

        //包含
        if (isNotNullOrEmpty(javaToJsonConfig.getIncludes())){
            jsonConfig.setJsonPropertyFilter(new ArrayContainsPropertyNamesPropertyFilter(javaToJsonConfig.getIncludes()));
        }
        return jsonConfig;
    }

    /**
     * Register json value processor.
     *
     * @param useJavaToJsonConfig
     *            the use java to json config
     * @param jsonConfig
     *            the json config
     */
    private static void registerJsonValueProcessor(JavaToJsonConfig useJavaToJsonConfig,JsonConfig jsonConfig){
        Map<String, ? extends JsonValueProcessor> propertyNameAndJsonValueProcessorMap = useJavaToJsonConfig
                        .getPropertyNameAndJsonValueProcessorMap();
        if (isNotNullOrEmpty(propertyNameAndJsonValueProcessorMap)){
            for (Map.Entry<String, ? extends JsonValueProcessor> entry : propertyNameAndJsonValueProcessorMap.entrySet()){
                jsonConfig.registerJsonValueProcessor(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * property name处理器.
     *
     * @param useJavaToJsonConfig
     *            the use java to json config
     * @param jsonConfig
     *            the json config
     */
    private static void registerJsonPropertyNameProcessor(JavaToJsonConfig useJavaToJsonConfig,JsonConfig jsonConfig){
        //property name处理器
        Map<Class<?>, PropertyNameProcessor> targetClassAndPropertyNameProcessorMap = useJavaToJsonConfig
                        .getJsonTargetClassAndPropertyNameProcessorMap();
        if (isNotNullOrEmpty(targetClassAndPropertyNameProcessorMap)){
            for (Map.Entry<Class<?>, PropertyNameProcessor> entry : targetClassAndPropertyNameProcessorMap.entrySet()){
                jsonConfig.registerJsonPropertyNameProcessor(entry.getKey(), entry.getValue());
            }
        }
    }

    //---------------------------------------------------------------

    /**
     * 默认的java to json JsonConfig.
     * 
     * <h3>含有以下的特性:</h3>
     * <blockquote>
     * <ol>
     * <li>{@link CycleDetectionStrategy#LENIENT} 避免循环引用</li>
     * <li>no IgnoreDefaultExcludes,默认过滤几个key "class", "declaringClass","metaClass"</li>
     * <li>
     * {@link DateJsonValueProcessor},如果是日期,自动渲染成 {@link DatePattern#COMMON_DATE_AND_TIME} 格式类型,如有需要可以使用
     * {@link JavaToJsonConfig#setPropertyNameAndJsonValueProcessorMap(Map)}覆盖此属性
     * </li>
     * <li>AllowNonStringKeys,允许非 string类型的key</li>
     * </ol>
     * </blockquote>
     *
     * @return the default json config
     * @see see net.sf.json.JsonConfig#DEFAULT_EXCLUDES
     * @see net.sf.json.util.CycleDetectionStrategy#LENIENT
     * 
     * @see <a href="http://feitianbenyue.iteye.com/blog/2046877">通过setAllowNonStringKeys解决java.lang.ClassCastException: JSON keys must be
     *      strings</a>
     */
    static JsonConfig buildDefaultJavaToJsonConfig(){
        JsonConfig jsonConfig = new JsonConfig();

        //see net.sf.json.JsonConfig#DEFAULT_EXCLUDES
        //默认会过滤的几个key "class", "declaringClass","metaClass"  
        jsonConfig.setIgnoreDefaultExcludes(false);

        // java.lang.ClassCastException: JSON keys must be strings
        // see http://feitianbenyue.iteye.com/blog/2046877
        jsonConfig.setAllowNonStringKeys(true);

        //排除,避免循环引用 There is a cycle in the hierarchy!
        //Returns empty array and null object
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

        // 注册日期处理器
        jsonConfig.registerJsonValueProcessor(Date.class, DateJsonValueProcessor.DEFAULT_INSTANCE);
        return jsonConfig;
    }
}
