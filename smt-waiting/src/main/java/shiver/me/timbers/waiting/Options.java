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

import static java.util.Arrays.asList;

/**
 * This class is used to customise the options for the {@link Waiter}. With this you can set the
 * {@link #withTimeout timeout}, {@link #withInterval interval}, {@link #includes includes}, {@link #excludes excludes},
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
 * smt.waiting.includes           # Comma separated list of names of classes that extend Throwable
 * smt.waiting.excludes           # Comma separated list of names of classes that extend Throwable
 * </code>
 *
 * @author Karl Bennett
 */
public class Options implements OptionsService {

    private final Chooser chooser;
    private final DefaultChoices defaultChoices;
    private final PropertyChoices propertyChoices;
    private final ManualChoices manualChoices;

    private Boolean withDefaults = false;
    private Long timeoutDuration;
    private TimeUnit timeoutUnit;
    private Long intervalDuration;
    private TimeUnit intervalUnit;
    private Boolean waitForTrue;
    private Boolean waitForNotNull;
    private List<ResultValidator> resultValidators = new ArrayList<>();
    private Boolean clearWaitFor = false;
    private Set<Class<? extends Throwable>> includes = new HashSet<>();
    private boolean clearIncludes = false;
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
     * Add any {@code ResultValidator}s that must pass before the method is considered to have succeeded in executing.
     */
    @Override
    public Options waitFor(ResultValidator... resultValidators) {
        this.resultValidators = asList(resultValidators);
        return this;
    }

    /**
     * If set to true any {@code ResultValidator}s set through global properties will be ignored.
     */
    @Override
    public Options clearWaitFor(boolean clearWaitFor) {
        this.clearWaitFor = clearWaitFor;
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
     * The {@code Waiter} will rerun the method being waited on if it throws any {@code Throwable}s added to the
     * includes list, any other {@code Throwable}s will be instantly rethrown and the method will not be rerun.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Options includes(Class<? extends Throwable>... includes) {
        this.includes = new HashSet<>(asList(includes));
        return this;
    }

    /**
     * If set to true any includes set through global properties will be ignored.
     */
    @Override
    public Options clearIncludes(boolean clearIncludes) {
        this.clearIncludes = clearIncludes;
        return this;
    }

    /**
     * The {@code Waiter} will not rerun the method being waited on if it throws any {@code Throwable}s added to the
     * excludes list, it will instead rethrow. Any other {@code Throwable}s will cause the method to be rerun.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Options excludes(Class<? extends Throwable>... excludes) {
        this.excludes = new HashSet<>(asList(excludes));
        return this;
    }

    /**
     * Generate the choices that will be used by the {@code Waiter}. There should be no need to manually call this
     * method.
     */
    @Override
    public Choice choose() {
        final Choices defaults = defaultChoices.create();
        final Choices properties = propertyChoices.apply(defaults, this);
        final Choices manual = manualChoices.apply(properties, this);
        return chooser.choose(manual);
    }

    public Boolean isWithDefaults() {
        return withDefaults;
    }

    public Boolean isClearWaitFor() {
        return clearWaitFor;
    }

    public boolean isClearIncludes() {
        return clearIncludes;
    }

    @Override
    public Long getTimeoutDuration() {
        return timeoutDuration;
    }

    @Override
    public TimeUnit getTimeoutUnit() {
        return timeoutUnit;
    }

    @Override
    public Long getIntervalDuration() {
        return intervalDuration;
    }

    @Override
    public TimeUnit getIntervalUnit() {
        return intervalUnit;
    }

    @Override
    public Boolean isWaitForTrue() {
        return waitForTrue;
    }

    @Override
    public Boolean isWaitForNotNull() {
        return waitForNotNull;
    }

    @Override
    public List<ResultValidator> getResultValidators() {
        return new ArrayList<>(resultValidators);
    }

    @Override
    public Set<Class<? extends Throwable>> getIncludes() {
        return new HashSet<>(includes);
    }

    @Override
    public Set<Class<? extends Throwable>> getExcludes() {
        return new HashSet<>(excludes);
    }
}
