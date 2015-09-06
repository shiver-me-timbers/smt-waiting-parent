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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Karl Bennett
 */
public class Options implements OptionsService {

    private static final long DEFAULT_TIMEOUT_DURATION = 10L;
    private static final TimeUnit DEFAULT_TIMEOUT_UNIT = SECONDS;
    private static final long DEFAULT_INTERVAL_DURATION = 100L;
    private static final TimeUnit DEFAULT_INTERVAL_UNIT = MILLISECONDS;

    private final Sleeper sleeper;

    private Long timeoutDuration;
    private TimeUnit timeoutUnit;
    private List<ResultValidator> resultValidators;
    private Long intervalDuration;
    private TimeUnit intervalUnit;

    public Options() {
        this(new ThreadSleeper(), new SystemPropertyParser());
    }

    Options(Sleeper sleeper, PropertyParser propertyParser) {
        this.sleeper = sleeper;
        this.timeoutDuration = propertyParser.getLongProperty("smt.waiting.timeout.duration", DEFAULT_TIMEOUT_DURATION);
        this.timeoutUnit = propertyParser.getEnumProperty("smt.waiting.timeout.unit", DEFAULT_TIMEOUT_UNIT);
        this.resultValidators = defaultValidators(propertyParser);
        this.intervalDuration = propertyParser.getLongProperty("smt.waiting.interval.duration", DEFAULT_INTERVAL_DURATION);
        this.intervalUnit = propertyParser.getEnumProperty("smt.waiting.interval.unit", DEFAULT_INTERVAL_UNIT);
    }

    private static List<ResultValidator> defaultValidators(PropertyParser propertyParser) {
        final List<ResultValidator> resultValidators = new ArrayList<>();

        if (propertyParser.getBooleanProperty("smt.waiting.waitForTrue", false)) {
            resultValidators.add(new TrueResult());
        }
        if (propertyParser.getBooleanProperty("smt.waiting.waitForNotNull", false)) {
            resultValidators.add(new NotNullResult());
        }

        final List<ResultValidator> customResultValidator = propertyParser.getInstanceProperty("smt.waiting.waitFor");
        for (ResultValidator validator : customResultValidator) {
            resultValidators.add(validator);
        }

        return resultValidators;
    }

    @Override
    public Options withDefaults() {
        this.timeoutDuration = DEFAULT_TIMEOUT_DURATION;
        this.timeoutUnit = DEFAULT_TIMEOUT_UNIT;
        this.resultValidators.clear();
        this.intervalDuration = DEFAULT_INTERVAL_DURATION;
        this.intervalUnit = DEFAULT_INTERVAL_UNIT;
        return this;
    }

    @Override
    public Options withTimeOut(Long duration, TimeUnit unit) {
        this.timeoutDuration = duration;
        this.timeoutUnit = unit;
        return this;
    }

    @Override
    public Options waitFor(ResultValidator resultValidator) {
        this.resultValidators.add(resultValidator);
        return this;
    }

    @Override
    public Options willWaitForTrue() {
        this.resultValidators.add(new TrueResult());
        return this;
    }

    @Override
    public Options willNotWaitForTrue() {
        removeValidator(TrueResult.class);
        return this;
    }

    @Override
    public Options willWaitForNotNull() {
        this.resultValidators.add(new NotNullResult());
        return this;
    }

    @Override
    public Options willNotWaitForNotNull() {
        removeValidator(NotNullResult.class);
        return this;
    }

    @Override
    public Options withInterval(long duration, TimeUnit unit) {
        this.intervalDuration = duration;
        this.intervalUnit = unit;
        return this;
    }

    @Override
    public Choice choose() {
        return new Choice(sleeper, timeoutDuration, timeoutUnit, resultValidators, intervalDuration, intervalUnit);
    }

    private void removeValidator(Class type) {
        for (Iterator<ResultValidator> iterator = resultValidators.iterator(); iterator.hasNext(); ) {
            final ResultValidator validator = iterator.next();
            if (type.equals(validator.getClass())) {
                iterator.remove();
            }
        }
    }
}
