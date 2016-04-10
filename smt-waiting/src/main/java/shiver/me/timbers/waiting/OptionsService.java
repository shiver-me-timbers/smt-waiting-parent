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

import java.util.concurrent.TimeUnit;

/**
 * @author Karl Bennett
 * @see Options
 */
interface OptionsService {

    /**
     * @see Options#withDefaults(boolean)
     */
    OptionsService withDefaults(boolean useDefaults);

    /**
     * @see Options#withTimeout(Long, TimeUnit)
     */
    OptionsService withTimeout(Long duration, TimeUnit unit);

    /**
     * @see Options#withInterval(long, TimeUnit)
     */
    OptionsService withInterval(long duration, TimeUnit unit);

    /**
     * @see Options#waitFor(ResultValidator)
     */
    OptionsService waitFor(ResultValidator resultValidator);

    /**
     * @see Options#willWaitForTrue(boolean)
     */
    OptionsService willWaitForTrue(boolean shouldWait);

    /**
     * @see Options#willWaitForNotNull(boolean)
     */
    OptionsService willWaitForNotNull(boolean shouldWait);

    /**
     * @see Options#includes(Class[])
     */
    @SuppressWarnings("unchecked")
    OptionsService includes(Class<? extends Throwable>... includes);

    /**
     * @see Options#excludes(Class[])
     */
    @SuppressWarnings("unchecked")
    OptionsService excludes(Class<? extends Throwable>... excludes);

    /**
     * @see Options#choose()
     */
    Choice choose();
}
