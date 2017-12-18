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

import static com.feilong.core.Validator.isNotNullOrEmpty;

import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.processors.PropertyNameProcessor;

/**
 * The Class JsonConfigBuilder.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.10.3
 */
final class JsonConfigBuilder{

    /**
     * Builds the.
     *
     * @param obj
     *            the obj
     * @param javaToJsonConfig
     *            the java to json config
     * @return the json config
     */
    static JsonConfig build(@SuppressWarnings("unused") Object obj,JavaToJsonConfig javaToJsonConfig){
        if (null == javaToJsonConfig){
            return null;
        }

        //-----------------------------------------------------------------

        JsonConfig jsonConfig = JsonHelper.buildDefaultJavaToJsonConfig();

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
        Map<String, JsonValueProcessor> propertyNameAndJsonValueProcessorMap = useJavaToJsonConfig
                        .getPropertyNameAndJsonValueProcessorMap();
        if (isNotNullOrEmpty(propertyNameAndJsonValueProcessorMap)){
            for (Map.Entry<String, JsonValueProcessor> entry : propertyNameAndJsonValueProcessorMap.entrySet()){
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
}
