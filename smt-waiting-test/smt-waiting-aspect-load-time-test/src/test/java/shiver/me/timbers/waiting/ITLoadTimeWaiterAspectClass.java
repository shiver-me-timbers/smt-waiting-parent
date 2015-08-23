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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WaiterConfiguration.class)
public class ITLoadTimeWaiterAspectClass extends ITWaiterAspect {

    @Autowired
    private WaitingDefaultsComponent defaultsComponent;

    @Autowired
    private WaitingDurationComponent durationComponent;

    @Autowired
    private WaitingForComponent waitingForComponent;

    @Autowired
    private WaitingForTrueComponent forTrueComponent;

    @Autowired
    private WaitingForNotNullComponent forNotNullComponent;

    @Autowired
    private WaitingIntervalComponent intervalComponent;

    @Override
    protected WaitingDefaultsComponent defaultsComponent() {
        return defaultsComponent;
    }

    @Override
    protected WaitingDurationComponent durationComponent() {
        return durationComponent;
    }

    @Override
    protected WaitingForComponent waitForComponent() {
        return waitingForComponent;
    }

    @Override
    protected WaitingForTrueComponent waitForTrueComponent() {
        return forTrueComponent;
    }

    @Override
    protected WaitingForNotNullComponent waitForNotNullComponent() {
        return forNotNullComponent;
    }

    @Override
    protected WaitingIntervalComponent intervalComponent() {
        return intervalComponent;
    }
}
