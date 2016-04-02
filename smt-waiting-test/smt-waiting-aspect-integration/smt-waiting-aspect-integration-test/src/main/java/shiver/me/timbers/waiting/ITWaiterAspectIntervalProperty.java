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

public abstract class ITWaiterAspectIntervalProperty extends ITWaiterAspectInterval {

    private PropertyManager properties;

    @Before
    public void setUp() {
        properties = new PropertyManager();
        setProperties(properties);
    }

    protected void setProperties(PropertyManager properties) {
        properties.setProperty("smt.waiting.interval.duration", "200");
        properties.setProperty("smt.waiting.interval.unit", MILLISECONDS.name());
    }

    @After
    public void tearDown() {
        properties.restoreProperties();
    }

    @Override
    protected WaitingIntervalComponent intervalComponent() {
        return new WaitingIntervalComponent() {
            @Override
            public <T> T intervalMethod(Callable<T> callable) throws Exception {
                return component().defaultsMethod(callable);
            }
        };
    }

    protected abstract WaitingDefaultsComponent component();
}