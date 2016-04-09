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

package shiver.me.timbers.waiting.property;

import java.util.HashMap;
import java.util.Map;

public class SystemPropertyManager implements PropertyManager {

    private final Map<String, String> properties = new HashMap<>();

    @Override
    public void setProperty(String key, String value) {
        properties.put(key, System.getProperty(key));
        System.setProperty(key, value);
    }

    @Override
    public void restoreProperties() {
        for (String key : properties.keySet()) {
            restoreProperty(key);
        }
    }

    private void restoreProperty(String key) {
        String value = properties.get(key);

        if (value == null) {
            System.clearProperty(key);
            return;
        }

        System.setProperty(key, value);
    }
}
