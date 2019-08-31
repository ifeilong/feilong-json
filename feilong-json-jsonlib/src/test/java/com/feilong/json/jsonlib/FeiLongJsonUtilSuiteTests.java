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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The Class FeiLongJsonUtilSuiteTests.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 */
@RunWith(Suite.class)
@SuiteClasses({ //

                FormatBeanIgnoreNullTest.class,

                FormatBeanErrorPropertyTest.class,

                ToArrayWithJsonToJavaConfigTest.class,

                ToListTest.class,
                ToListWithJsonToJavaConfigTest.class,

                ToBeanTest.class,
                ToBeanJsonStringMoreAttributeTest.class,
                ToBeanWithJsonToJavaConfigTest.class,

                ToMapTest.class,
                ToMapWithJsonToJavaConfigTest.class,

                FormatBeanSensitiveWordsTest.class,
                FormatBeanSensitiveWordsNoTest.class,

                FormatBeanBigDecimalTest.class,

                FormatBeanFileTest.class,
                FormatBeanXMLGregorianCalendarTest.class,

                FormatStringTest.class,

                JsonHelperTest.class,

        //
})
public class FeiLongJsonUtilSuiteTests{

}
