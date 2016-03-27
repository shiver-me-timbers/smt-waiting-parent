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

import org.junit.After;
import org.junit.Before;

import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public abstract class ITWaiterAspectWaitForProperty extends ITWaiterAspectWaitFor {

    private PropertyManager properties;

    @Before
    public void setUp() {
        properties = new PropertyManager();
        setProperties(properties);
    }

    protected void setProperties(PropertyManager properties) {
        properties.setProperty("smt.waiting.timeout.duration", "500");
        properties.setProperty("smt.waiting.timeout.unit", MILLISECONDS.name());
        properties.setProperty("smt.waiting.waitFor", ValidResult.class.getName());
    }

    @After
    public void tearDown() {
        properties.restoreProperties();
    }

    @Override
    protected WaitingForComponent waitForComponent() {
        return new WaitingForComponent() {
            @Override
            public <T> T waitingForMethod(Callable<T> callable) throws Exception {
                return component().defaultsMethod(callable);
            }
        };
    }

    protected abstract WaitingDefaultsComponent component();
}
