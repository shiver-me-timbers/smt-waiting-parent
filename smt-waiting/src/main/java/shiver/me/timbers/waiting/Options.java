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
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
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

    @Override
    public Options withDefaults(boolean useDefaults) {
        this.withDefaults = useDefaults;
        return this;
    }

    @Override
    public Options withTimeOut(Long duration, TimeUnit unit) {
        this.timeoutDuration = duration;
        this.timeoutUnit = unit;
        return this;
    }

    @Override
    public Options willWaitForTrue(boolean shouldWait) {
        this.waitForTrue = shouldWait;
        return this;
    }

    @Override
    public Options willWaitForNotNull(boolean shouldWait) {
        this.waitForNotNull = shouldWait;
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
                resultValidators
            )
        );
        return chooser.choose(manual);
    }
}
