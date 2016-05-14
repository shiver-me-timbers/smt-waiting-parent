/*
 * Copyright 2016 Karl Bennett
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

import shiver.me.timbers.waiting.execution.SpringManualClearWaitingExclude;
import shiver.me.timbers.waiting.execution.SpringManualWaitingDefaults;
import shiver.me.timbers.waiting.execution.SpringManualWaitingExclude;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingExclude;

import java.util.concurrent.TimeUnit;

public class ITManualSpringWaiterExcludeProperty extends AbstractITSpringWaiterExcludeProperty {

    @Override
    public WaitingDefaults defaults() {
        return new SpringManualWaitingDefaults();
    }

    @Override
    protected WaitingExclude addExclude(long duration, TimeUnit unit, Throwable exclude) {
        return new SpringManualWaitingExclude(duration, unit, exclude);
    }

    @Override
    protected WaitingExclude clearThenAddExclude(
        long duration,
        TimeUnit unit,
        boolean clearExcludes,
        Throwable exclude
    ) {
        return new SpringManualClearWaitingExclude(duration, unit, clearExcludes, exclude);
    }
}
