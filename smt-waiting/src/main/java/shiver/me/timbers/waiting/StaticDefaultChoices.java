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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Karl Bennett
 */
class StaticDefaultChoices implements DefaultChoices {

    private static final long DEFAULT_TIMEOUT_DURATION = 10L;
    private static final TimeUnit DEFAULT_TIMEOUT_UNIT = SECONDS;
    private static final long DEFAULT_INTERVAL_DURATION = 100L;
    private static final TimeUnit DEFAULT_INTERVAL_UNIT = MILLISECONDS;

    @Override
    public Choices create() {
        return new BasicChoices(
            DEFAULT_TIMEOUT_DURATION,
            DEFAULT_TIMEOUT_UNIT,
            DEFAULT_INTERVAL_DURATION,
            DEFAULT_INTERVAL_UNIT,
            false,
            false,
            new ArrayList<ResultValidator>(),
            new HashSet<Class<? extends Throwable>>(),
            new HashSet<Class<? extends Throwable>>()
        );
    }
}
