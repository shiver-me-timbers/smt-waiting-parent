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
import java.util.Collections;
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
    private final PropertyParser propertyParser;

    private Long timeoutDuration;
    private TimeUnit timeoutUnit;
    private Long intervalDuration;
    private TimeUnit intervalUnit;
    private Boolean waitForTrue;
    private Boolean waitForNotNull;
    private List<ResultValidator> resultValidators = new ArrayList<>();
    private Boolean withDefaults = false;

    public Options() {
        this(new ThreadSleeper(), new SystemPropertyParser());
    }

    Options(Sleeper sleeper, PropertyParser propertyParser) {
        this.sleeper = sleeper;
        this.propertyParser = propertyParser;
    }

    @Override
    public Options withDefaults() {
        this.withDefaults = true;
        return this;
    }

    @Override
    public Options withTimeOut(Long duration, TimeUnit unit) {
        this.timeoutDuration = duration;
        this.timeoutUnit = unit;
        return this;
    }

    @Override
    public Options willWaitForTrue() {
        this.waitForTrue = true;
        return this;
    }

    @Override
    public Options willNotWaitForTrue() {
        this.waitForTrue = false;
        return this;
    }

    @Override
    public Options willWaitForNotNull() {
        this.waitForNotNull = true;
        return this;
    }

    @Override
    public Options willNotWaitForNotNull() {
        this.waitForNotNull = false;
        return this;
    }

    @Override
    public Options withInterval(long duration, TimeUnit unit) {
        this.intervalDuration = duration;
        this.intervalUnit = unit;
        return this;
    }

    @Override
    public Options waitFor(ResultValidator resultValidator) {
        this.resultValidators.add(resultValidator);
        return this;
    }

    @Override
    public Choice choose() {
        ChosenOptions options = createFromDefaults();
        options = applyProperties(options);
        options = applyManualOptions(options);

        if (options.waitForTrue) {
            options.resultValidators.add(new TrueResult());
        }
        if (options.waitForNotNull) {
            options.resultValidators.add(new NotNullResult());
        }

        return new Choice(
            sleeper,
            options.timeoutDuration,
            options.timeoutUnit,
            options.resultValidators,
            options.intervalDuration,
            options.intervalUnit
        );
    }

    private ChosenOptions createFromDefaults() {
        return new ChosenOptions(
            DEFAULT_TIMEOUT_DURATION,
            DEFAULT_TIMEOUT_UNIT,
            DEFAULT_INTERVAL_DURATION,
            DEFAULT_INTERVAL_UNIT,
            false,
            false,
            Collections.<ResultValidator>emptyList()
        );
    }

    private ChosenOptions applyProperties(ChosenOptions options) {
        if (withDefaults) {
            return options;
        }
        return new ChosenOptions(
            propertyParser.getLongProperty("smt.waiting.timeout.duration", options.timeoutDuration),
            propertyParser.getEnumProperty("smt.waiting.timeout.unit", options.timeoutUnit),
            propertyParser.getLongProperty("smt.waiting.interval.duration", options.intervalDuration),
            propertyParser.getEnumProperty("smt.waiting.interval.unit", options.intervalUnit),
            propertyParser.getBooleanProperty("smt.waiting.waitForTrue", false),
            propertyParser.getBooleanProperty("smt.waiting.waitForNotNull", false),
            defaultValidators(options.resultValidators, propertyParser)
        );
    }

    private ChosenOptions applyManualOptions(ChosenOptions options) {
        options.resultValidators.addAll(resultValidators);
        return new ChosenOptions(
            timeoutDuration == null ? options.timeoutDuration : timeoutDuration,
            timeoutUnit == null ? options.timeoutUnit : timeoutUnit,
            intervalDuration == null ? options.intervalDuration : intervalDuration,
            intervalUnit == null ? options.intervalUnit : intervalUnit,
            waitForTrue == null ? options.waitForTrue : waitForTrue,
            waitForNotNull == null ? options.waitForNotNull : waitForNotNull,
            options.resultValidators
        );
    }

    private static List<ResultValidator> defaultValidators(
        List<ResultValidator> resultValidators,
        PropertyParser propertyParser
    ) {

        final List<ResultValidator> customResultValidator = propertyParser.getInstanceProperty("smt.waiting.waitFor");
        for (ResultValidator validator : customResultValidator) {
            resultValidators.add(validator);
        }

        return resultValidators;
    }

    private static class ChosenOptions {

        private final Long timeoutDuration;
        private final TimeUnit timeoutUnit;
        private final Long intervalDuration;
        private final TimeUnit intervalUnit;
        private final boolean waitForTrue;
        private final boolean waitForNotNull;
        private final List<ResultValidator> resultValidators;

        private ChosenOptions(
            Long timeoutDuration,
            TimeUnit timeoutUnit,
            Long intervalDuration,
            TimeUnit intervalUnit,
            boolean waitForTrue,
            boolean waitForNotNull,
            List<ResultValidator> resultValidators
        ) {
            this.timeoutDuration = timeoutDuration;
            this.timeoutUnit = timeoutUnit;
            this.intervalDuration = intervalDuration;
            this.intervalUnit = intervalUnit;
            this.waitForTrue = waitForTrue;
            this.waitForNotNull = waitForNotNull;
            this.resultValidators = new ArrayList<>(resultValidators);
        }
    }
}
