/*
 * Copyright 2015 Karl Bennett
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;

/**
 * @author Karl Bennett
 */
@Configurable
class SpringPropertyGetter implements PropertyGetter {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public String get(String key, Object defaultValue) {

        if (applicationContext == null) {
            return defaultValue.toString();
        }

        return applicationContext.getEnvironment().getProperty(key, defaultValue.toString());
    }

    @Override
    public String get(String key) {

        if (applicationContext == null) {
            return null;
        }

        return applicationContext.getEnvironment().getProperty(key);
    }
}
