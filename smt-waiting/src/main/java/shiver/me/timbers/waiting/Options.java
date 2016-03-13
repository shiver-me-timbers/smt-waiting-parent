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
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * This class is used to customise the options for the {@link Waiter}. With this you can set the
 * {@link #withTimeout timeout}, {@link #withInterval interval}, {@link #include includes}, {@link #exclude excludes},
 * {@link #waitFor validators}, or whether the the method being waited on must return {@link #willWaitForTrue true} or a
 * non {@link #willWaitForNotNull null} result.
 * <p>
 * The {@code Options} can also be configured globally with the following properties.
 * <code>
 * smt.waiting.timeout.duration  # Long
 * smt.waiting.timeout.unit      # Name of one of the java.util.concurrent.TimeUnit enums
 * smt.waiting.interval.duration # Long
 * smt.waiting.interval.unit     # Name of one of the java.util.concurrent.TimeUnit enums
 * smt.waiting.waitForTrue       # boolean
 * smt.waiting.waitForNotNull    # boolean
 * smt.waiting.waitFor           # Name of class with a default constructor that implements ResultValidator
 * smt.waiting.include           # Comma separated list of names of classes that extend Throwable
 * smt.waiting.exclude           # Comma separated list of names of classes that extend Throwable
 * </code>
 *
 * @author Karl Bennett
 */
public class Options implements OptionsService {

    private final Chooser chooser;
    private final DefaultChoices defaultChoices;
    private final PropertyChoices propertyChoices;
    private final ManualChoices manualChoices;

    private Long timeoutDuration;
    private TimeUnit timeoutUnit;
    private Long intervalDuration;
    private TimeUnit intervalUnit;
    private Boolean waitForTrue;
    private Boolean waitForNotNull;
    private List<ResultValidator> resultValidators = new ArrayList<>();
    private Boolean withDefaults = false;
    private Set<Class<? extends Throwable>> includes = new HashSet<>();
    private Set<Class<? extends Throwable>> excludes = new HashSet<>();

    public Options() {
        this(
            new StaticDefaultChoices(),
            new PropertyParserChoices(new SystemPropertyParser()),
            new MergingManualChoices(),
            new BasicChooser(new ThreadSleeper())
        );
    }

    Options(
        DefaultChoices defaultChoices,
        PropertyChoices propertyChoices,
        ManualChoices manualChoices,
        Chooser chooser
    ) {
        this.defaultChoices = defaultChoices;
        this.propertyChoices = propertyChoices;
        this.manualChoices = manualChoices;
        this.chooser = chooser;
    }

    /**
     * Should the {@code Waiter} start with the default choices instead of the globally
     * configured choices before applying choices from these options.
     */
    @Override
    public Options withDefaults(boolean useDefaults) {
        this.withDefaults = useDefaults;
        return this;
    }

    /**
     * Set how long we should wait for the method to succeed.
     */
    @Override
    public Options withTimeout(Long duration, TimeUnit unit) {
        this.timeoutDuration = duration;
        this.timeoutUnit = unit;
        return this;
    }

    /**
     * Set how long we should wait in between executions of the method.
     */
    @Override
    public Options withInterval(long duration, TimeUnit unit) {
        this.intervalDuration = duration;
        this.intervalUnit = unit;
        return this;
    }

    /**
     * Add a {@code ResultValidator} that must pass before the method is considered to have succeeded in executing.
     * Additional calls to this method will add more {@code ResultValidator}s.
     */
    @Override
    public Options waitFor(ResultValidator resultValidator) {
        this.resultValidators.add(resultValidator);
        return this;
    }

    /**
     * If set to {@code true} the methods execution will not be considered successful until it returns true.
     */
    @Override
    public Options willWaitForTrue(boolean shouldWait) {
        this.waitForTrue = shouldWait;
        return this;
    }

    /**
     * If set to {@code true} the methods execution will not be considered successful until it returns a nonnull value.
     */
    @Override
    public Options willWaitForNotNull(boolean shouldWait) {
        this.waitForNotNull = shouldWait;
        return this;
    }

    /**
     * The {@code Waiter} will rerun the method being waited on if it throws any {@code Throwable}s added to the include
     * list, any other {@code Throwable} will be instantly rethrown and the method will not be rerun.
     */
    @Override
    public Options include(Class<? extends Throwable> throwable) {
        this.includes.add(throwable);
        return this;
    }

    /**
     * The {@code Waiter} will instantly rethrown any {@code Throwable}s added to the exclude list by the method being
     * waited on, any other {@code Throwable} will cause the method to be rerun.
     */
    @Override
    public Options exclude(Class<? extends Throwable> throwable) {
        this.excludes.add(throwable);
        return this;
    }

    /**
     * Generate the choices that will be used by the {@code Waiter}. There should be no need to manually call this
     * method.
     */
    @Override
    public Choice choose() {
        final Choices defaults = defaultChoices.create();
        final Choices manual = manualChoices.apply(
            withDefaults ? defaults : propertyChoices.apply(defaults),
            new BasicChoices(
                timeoutDuration,
                timeoutUnit,
                intervalDuration,
                intervalUnit,
                waitForTrue,
                waitForNotNull,
                resultValidators,
                includes,
                excludes
            )
        );
        return chooser.choose(manual);
    }
}
