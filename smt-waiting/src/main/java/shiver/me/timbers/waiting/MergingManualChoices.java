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

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Karl Bennett
 */
class MergingManualChoices implements ManualChoices {

    @Override
    public Choices apply(Choices currentChoices, Choices manualChoices) {
        return new BasicChoices(
            overrideTimeoutDuration(currentChoices, manualChoices),
            overrideTimeoutUnit(currentChoices, manualChoices),
            overrideIntervalDuration(currentChoices, manualChoices),
            overrideIntervalUnit(currentChoices, manualChoices),
            overrideWaitForTrue(currentChoices, manualChoices),
            overrideWaitForNotNull(currentChoices, manualChoices),
            overrideResultValidators(currentChoices, manualChoices),
            overrideIncludes(currentChoices, manualChoices),
            overrideExcludes(currentChoices, manualChoices)
        );
    }

    private Long overrideTimeoutDuration(Choices currentChoices, Choices manualChoices) {
        final Long timeoutDuration = manualChoices.getTimeoutDuration();
        return timeoutDuration == null ? currentChoices.getTimeoutDuration() : timeoutDuration;
    }

    private TimeUnit overrideTimeoutUnit(Choices currentChoices, Choices manualChoices) {
        final TimeUnit timeoutUnit = manualChoices.getTimeoutUnit();
        return timeoutUnit == null ? currentChoices.getTimeoutUnit() : timeoutUnit;
    }

    private Long overrideIntervalDuration(Choices currentChoices, Choices manualChoices) {
        final Long intervalDuration = manualChoices.getIntervalDuration();
        return intervalDuration == null ? currentChoices.getIntervalDuration() : intervalDuration;
    }

    private TimeUnit overrideIntervalUnit(Choices currentChoices, Choices manualChoices) {
        final TimeUnit intervalUnit = manualChoices.getIntervalUnit();
        return intervalUnit == null ? currentChoices.getIntervalUnit() : intervalUnit;
    }

    private Boolean overrideWaitForTrue(Choices currentChoices, Choices manualChoices) {
        final Boolean waitForTrue = manualChoices.isWaitForTrue();
        return waitForTrue == null ? currentChoices.isWaitForTrue() : waitForTrue;
    }

    private Boolean overrideWaitForNotNull(Choices currentChoices, Choices manualChoices) {
        final Boolean waitForNotNull = manualChoices.isWaitForNotNull();
        return waitForNotNull == null ? currentChoices.isWaitForNotNull() : waitForNotNull;
    }

    private List<ResultValidator> overrideResultValidators(Choices currentChoices, Choices manualChoices) {
        final List<ResultValidator> resultValidators = currentChoices.getResultValidators();
        resultValidators.addAll(manualChoices.getResultValidators());
        return resultValidators;
    }

    private Set<Class<? extends Throwable>> overrideIncludes(Choices currentChoices, Choices manualChoices) {
        final Set<Class<? extends Throwable>> resultValidators = currentChoices.getIncludes();
        resultValidators.addAll(manualChoices.getIncludes());
        return resultValidators;
    }

    private Set<Class<? extends Throwable>> overrideExcludes(Choices currentChoices, Choices manualChoices) {
        final Set<Class<? extends Throwable>> resultValidators = currentChoices.getExcludes();
        resultValidators.addAll(manualChoices.getExcludes());
        return resultValidators;
    }
}
