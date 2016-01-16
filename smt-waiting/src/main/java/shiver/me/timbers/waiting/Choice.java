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

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Karl Bennett
 */
class Choice {

    private final Sleeper sleeper;

    private final Long timeoutDuration;
    private final TimeUnit timeoutUnit;
    private final Long intervalDuration;
    private final TimeUnit intervalUnit;
    private final List<ResultValidator> resultValidators;
    private final Set<Class<? extends Throwable>> includes;
    private final Set<Class<? extends Throwable>> excludes;

    Choice(
        Sleeper sleeper,
        Long timeoutDuration,
        TimeUnit timeoutUnit,
        Long intervalDuration,
        TimeUnit intervalUnit,
        List<ResultValidator> resultValidators,
        Set<Class<? extends Throwable>> includes,
        Set<Class<? extends Throwable>> excludes
    ) {
        this.sleeper = sleeper;
        this.timeoutDuration = timeoutDuration;
        this.timeoutUnit = timeoutUnit;
        this.intervalDuration = intervalDuration;
        this.intervalUnit = intervalUnit;
        this.resultValidators = resultValidators;
        this.includes = includes;
        this.excludes = excludes;
    }

    Timer startTimer() {
        return new Timer(timeoutDuration, timeoutUnit, new Start(new Date()));
    }

    @SuppressWarnings("unchecked")
    boolean isValid(Object result) throws Throwable {
        for (ResultValidator resultValidator : resultValidators) {
            if (!resultValidator.isValid(result)) {
                return false;
            }
        }
        return true;
    }

    void interval() {
        try {
            sleeper.sleep(intervalUnit.toMillis(intervalDuration));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSuppressed(Throwable throwable) {
        if (includes.isEmpty() && excludes.isEmpty()) {
            return true;
        }

        final Class<? extends Throwable> type = throwable.getClass();

        if (includes.contains(type)) {
            return true;
        }

        return false;
    }
}
