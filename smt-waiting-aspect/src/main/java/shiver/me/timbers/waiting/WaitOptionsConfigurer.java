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

import static shiver.me.timbers.waiting.Decision.UNDECIDED;
import static shiver.me.timbers.waiting.Decision.YES;

/**
 * @author Karl Bennett
 */
class WaitOptionsConfigurer implements OptionsConfigurer<Wait> {

    @Override
    public OptionsService apply(OptionsService options, Wait wait) {
        applyTimeout(options, wait);
        applyInterval(options, wait);
        applyResultValidators(options, wait);
        applyWaitForTrue(options, wait);
        applyWaitForNotNull(options, wait);
        applyIncludes(options, wait);
        applyExcludes(options, wait);
        applyWithDefaults(options, wait);
        applyClearWaitFor(options, wait);
        applyClearInclude(options, wait);
        applyClearExclude(options, wait);
        return options;
    }

    private static void applyTimeout(OptionsService options, Wait wait) {
        final Timeout timeout = wait.value();
        if (timeout.duration() > -1) {
            options.withTimeout(timeout.duration(), timeout.unit());
        }
    }

    private static void applyInterval(OptionsService options, Wait wait) {
        final Interval interval = wait.interval();
        if (interval.duration() > -1) {
            options.withInterval(interval.duration(), interval.unit());
        }
    }

    private static void applyResultValidators(OptionsService options, Wait wait) {
        final Class<? extends ResultValidator>[] waitFor = wait.waitFor();
        if (waitFor.length > 0) {
            options.waitFor(newInstances(waitFor));
        }
    }

    private static void applyWaitForTrue(OptionsService options, Wait wait) {
        final Decision waitForTrue = wait.waitForTrue();
        if (!UNDECIDED.equals(waitForTrue)) {
            options.willWaitForTrue(YES.equals(waitForTrue));
        }
    }

    private static void applyWaitForNotNull(OptionsService options, Wait wait) {
        final Decision waitForNotNull = wait.waitForNotNull();
        if (!UNDECIDED.equals(waitForNotNull)) {
            options.willWaitForNotNull(YES.equals(waitForNotNull));
        }
    }

    private static void applyIncludes(OptionsService options, Wait wait) {
        final Class<? extends Throwable>[] includes = wait.includes();
        if (includes.length > 0) {
            options.includes(wait.includes());
        }
    }

    private static void applyExcludes(OptionsService options, Wait wait) {
        final Class<? extends Throwable>[] excludes = wait.excludes();
        if (excludes.length > 0) {
            options.excludes(excludes);
        }
    }

    private static void applyWithDefaults(OptionsService options, Wait wait) {
        final boolean withDefaults = wait.withDefaults();
        if (withDefaults == true) {
            options.withDefaults(withDefaults);
        }
    }

    private static void applyClearWaitFor(OptionsService options, Wait wait) {
        final boolean clearWaitFor = wait.clearWaitFor();
        if (clearWaitFor == true) {
            options.clearWaitFor(clearWaitFor);
        }
    }

    private void applyClearInclude(OptionsService options, Wait wait) {
        final boolean clearIncludes = wait.clearIncludes();
        if (clearIncludes == true) {
            options.clearIncludes(clearIncludes);
        }
    }

    private void applyClearExclude(OptionsService options, Wait wait) {
        final boolean clearExcludes = wait.clearExcludes();
        if (clearExcludes == true) {
            options.clearExcludes(clearExcludes);
        }
    }

    private static ResultValidator[] newInstances(Class<? extends ResultValidator>[] waitFor) {
        final ResultValidator[] validators = new ResultValidator[waitFor.length];
        for (int i = 0; i < waitFor.length; i++) {
            validators[i] = newInstance(waitFor[i]);
        }
        return validators;
    }

    private static <T> T newInstance(Class<T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
