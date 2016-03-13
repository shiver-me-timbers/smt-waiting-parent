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

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WaiterConfiguration.class)
public class ITLoadTimeWaiterAspectMethods extends ITWaiterAspect {

    private WaitingComponent component = new WaitingMethodsComponent();

    @Override
    protected WaitingDefaultsComponent defaultsComponent() {
        return component;
    }

    @Override
    protected WaitingTimeoutComponent timeoutComponent() {
        return component;
    }

    @Override
    protected WaitingForComponent waitForComponent() {
        return component;
    }

    @Override
    protected WaitingForTrueComponent waitForTrueComponent() {
        return component;
    }

    @Override
    protected WaitingForNotNullComponent waitForNotNullComponent() {
        return component;
    }

    @Override
    protected WaitingIntervalComponent intervalComponent() {
        return component;
    }

    @Override
    protected WaitingIncludeComponent includeComponent() {
        return component;
    }

    @Override
    protected WaitingExcludeComponent excludeComponent() {
        return component;
    }
}