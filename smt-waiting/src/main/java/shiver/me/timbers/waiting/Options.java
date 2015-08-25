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
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Karl Bennett
 */
public class Options implements OptionsService {

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
        this.timeoutDuration = propertyParser.getLongProperty("smt.waiting.timeout.duration", 10L);
        this.timeoutUnit = propertyParser.getEnumProperty("smt.waiting.timeout.unit", SECONDS);
        this.resultValidators = defaultValidators(propertyParser);
        this.intervalDuration = propertyParser.getLongProperty("smt.waiting.interval.duration", 100L);
        this.intervalUnit = propertyParser.getEnumProperty("smt.waiting.interval.unit", MILLISECONDS);
    }

    private static List<ResultValidator> defaultValidators(PropertyParser propertyParser) {
        final List<ResultValidator> resultValidators = new ArrayList<>();

        if (propertyParser.getBooleanProperty("smt.waiting.waitForTrue", false)) {
            resultValidators.add(new TrueResult());
        }
        if (propertyParser.getBooleanProperty("smt.waiting.waitForNotNull", false)) {
            resultValidators.add(new NotNullResult());
        }

        final ResultValidator customResultValidator = propertyParser.getInstanceProperty("smt.waiting.waitFor", null);
        if (customResultValidator != null) {
            resultValidators.add(customResultValidator);
        }

        return resultValidators;
    }

    @Override
    public Options withTimeOut(Long duration, TimeUnit unit) {
        this.timeoutDuration = duration;
        this.timeoutUnit = unit;
        return this;
    }

    Timer startTimer() {
        return new Timer(timeoutDuration, timeoutUnit, new Start(new Date()));
    }

    @Override
    public Options waitFor(ResultValidator resultValidator) {
        this.resultValidators.add(resultValidator);
        return this;
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

    @Override
    public Options willWaitForTrue() {
        this.resultValidators.add(new TrueResult());
        return this;
    }

    @Override
    public Options willWaitForNotNull() {
        this.resultValidators.add(new NotNullResult());
        return this;
    }

    @Override
    public Options withInterval(long duration, TimeUnit unit) {
        this.intervalDuration = duration;
        this.intervalUnit = unit;
        return this;
    }

    void interval() {
        try {
            sleeper.sleep(intervalUnit.toMillis(intervalDuration));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
