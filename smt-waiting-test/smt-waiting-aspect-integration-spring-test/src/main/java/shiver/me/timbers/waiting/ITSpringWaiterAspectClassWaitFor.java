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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shiver.me.timbers.WaiterWaitForConfiguration;

import java.util.concurrent.Callable;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WaiterWaitForConfiguration.class)
@DirtiesContext(classMode = AFTER_CLASS)
public abstract class ITSpringWaiterAspectClassWaitFor extends ITWaiterAspectWaitFor {

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
