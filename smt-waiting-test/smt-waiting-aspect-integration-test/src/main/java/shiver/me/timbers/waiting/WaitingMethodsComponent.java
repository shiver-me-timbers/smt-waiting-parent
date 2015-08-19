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

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public class WaitingMethodsComponent implements WaitingComponent {

    @Wait
    @Override
    public <T> T defaultsMethod(Callable<T> callable) throws Exception {
        return callable.call();
    }

    @Wait(@TimeOut(duration = 500, unit = MILLISECONDS))
    @Override
    public <T> T durationSetMethod(Callable<T> callable) throws Exception {
        return callable.call();
    }

    @Wait(value = @TimeOut(duration = 500, unit = MILLISECONDS), waitForTrue = true)
    @Override
    public <T> T waitForTrueMethod(Callable<T> callable) throws Exception {
        return callable.call();
    }

    @Wait(value = @TimeOut(duration = 500, unit = MILLISECONDS), waitForNotNull = true)
    @Override
    public <T> T waitForNotNullMethod(Callable<T> callable) throws Exception {
        return callable.call();
    }

    @Wait(value = @TimeOut(duration = 500, unit = MILLISECONDS), waitFor = TestValidResult.class)
    @Override
    public <T> T waitingForMethod(Callable<T> callable) throws Exception {
        return callable.call();
    }

    @Wait(
        value = @TimeOut(duration = 500, unit = MILLISECONDS),
        interval = @Interval(duration = 200, unit = MILLISECONDS)
    )
    @Override
    public <T> T intervalMethod(Callable<T> callable) throws Exception {
        return callable.call();
    }
}