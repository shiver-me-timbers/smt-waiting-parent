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
import shiver.me.timbers.WaiterDefaultConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WaiterDefaultConfiguration.class)
public class ITSpringLoadTimeWaiterAspectClass extends ITWaiterAspect {

    @Override
    protected WaitingDefaultsComponent defaultsComponent() {
        return new WaitingDefaultsClassComponent();
    }

    @Override
    protected WaitingTimeoutComponent timeoutComponent() {
        return new WaitingTimeoutClassComponent();
    }

    @Override
    protected WaitingForComponent waitForComponent() {
        return new WaitingForClassComponent();
    }

    @Override
    protected WaitingForTrueComponent waitForTrueComponent() {
        return new WaitingForTrueClassComponent();
    }

    @Override
    protected WaitingForNotNullComponent waitForNotNullComponent() {
        return new WaitingForNotNullClassComponent();
    }

    @Override
    protected WaitingIntervalComponent intervalComponent() {
        return new WaitingIntervalClassComponent();
    }

    @Override
    protected WaitingIncludeComponent includeComponent() {
        return new WaitingIncludeClassComponent();
    }

    @Override
    protected WaitingExcludeComponent excludeComponent() {
        return new WaitingExcludeClassComponent();
    }
}