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
        final TimeOut timeOut = wait.value();
        if (timeOut.duration() > -1) {
            options.withTimeOut(timeOut.duration(), timeOut.unit());
        }

        final Interval interval = wait.interval();
        if (interval.duration() > -1) {
            options.withInterval(interval.duration(), interval.unit());
        }

        final Class<? extends ResultValidator>[] waitFor = wait.waitFor();
        for (Class<? extends ResultValidator> validator : waitFor) {
            options.waitFor(newInstance(validator));
        }

        if (!UNDECIDED.equals(wait.waitForTrue())) {
            options.willWaitForTrue(YES.equals(wait.waitForTrue()));
        }
        if (!UNDECIDED.equals(wait.waitForNotNull())) {
            options.willWaitForNotNull(YES.equals(wait.waitForNotNull()));
        }

        return options;
    }

    private <T> T newInstance(Class<T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
