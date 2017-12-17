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

import java.util.Map;

import org.apache.commons.lang3.Validate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * json处理工具类.
 * 
 * <h3>依赖:</h3>
 * 
 * <blockquote>
 * 
 * <pre class="code">
 * {@code 
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.35</version>
</dependency>
}
 * </pre>
 * 
 * </blockquote>
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @see <a href="https://github.com/alibaba/fastjson">fastjson</a>
 * @see <a href="https://github.com/alibaba/fastjson/wiki/Samples-DataBind">快速入门</a>
 * @see <a href="https://github.com/darcyliu/google-styleguide/blob/master/JSONStyleGuide.md">Google JSON 风格指南</a>
 * @since 1.10.5
 */
public final class FastJsonUtil{

    public static String format(Object obj){
        return JSON.toJSONString(obj, SerializerFeature.PrettyFormat);
    }

    public static <T> T toBean(String json,Class<T> rootClass){
        if (null == json){
            return null;
        }
        Validate.notNull(rootClass, "rootClass can't be null!");
        return JSON.parseObject(json, rootClass);
    }

    public static <T> Map<String, T> toMap(String json){
        if (null == json){
            return null;
        }
        return toBean(json, Map.class);
    }
}