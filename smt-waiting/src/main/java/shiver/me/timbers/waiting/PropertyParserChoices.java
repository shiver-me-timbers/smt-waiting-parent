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
 * @author Karl Bennett
 */
class PropertyParserChoices implements PropertyChoices {

    private final PropertyParser propertyParser;

    public PropertyParserChoices(PropertyParser propertyParser) {
        this.propertyParser = propertyParser;
    }

    @Override
    public Choices apply(Choices currentChoices, Options options) {
        if (options.isWithDefaults()) {
            return currentChoices;
        }

        return new BasicChoices(
            findLongProperty("smt.waiting.timeout.duration", currentChoices.getTimeoutDuration()),
            findEnumProperty("smt.waiting.timeout.unit", currentChoices.getTimeoutUnit()),
            findLongProperty("smt.waiting.interval.duration", currentChoices.getIntervalDuration()),
            findEnumProperty("smt.waiting.interval.unit", currentChoices.getIntervalUnit()),
            findBooleanProperty("smt.waiting.waitForTrue", currentChoices.isWaitForTrue()),
            findBooleanProperty("smt.waiting.waitForNotNull", currentChoices.isWaitForNotNull()),
            findResultValidators("smt.waiting.waitFor", currentChoices, options),
            findClassProperty("smt.waiting.includes", currentChoices.getIncludes()),
            findClassProperty("smt.waiting.excludes", currentChoices.getExcludes())
        );
    }

    private Boolean findBooleanProperty(String key, Boolean waitForTrue) {
        return propertyParser.getBooleanProperty(key, waitForTrue);
    }

    private Long findLongProperty(String key, Long timeoutDuration) {
        return propertyParser.getLongProperty(key, timeoutDuration);
    }

    private TimeUnit findEnumProperty(String key, TimeUnit timeoutUnit) {
        return propertyParser.getEnumProperty(key, timeoutUnit);
    }

    @SuppressWarnings("unchecked")
    private Set<Class<? extends Throwable>> findClassProperty(String key, Set<Class<? extends Throwable>> throwables) {
        return toSet(propertyParser.getClassProperty(key, toList(throwables)));
    }

    private List<ResultValidator> findResultValidators(String key, Choices currentChoices, Options options) {
        if (options.isClearWaitFor()) {
            return options.getResultValidators();
        }

        return propertyParser.getInstanceProperty(key, currentChoices.getResultValidators());
    }

    @SuppressWarnings("unchecked")
    private static List toList(Set includes) {
        return new ArrayList(includes);
    }

    @SuppressWarnings("unchecked")
    private static HashSet<Class<? extends Throwable>> toSet(List classProperty) {
        return new HashSet<>(classProperty);
    }
}
