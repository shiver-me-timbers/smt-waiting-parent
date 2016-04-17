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

/**
 * @author Karl Bennett
 */
class PropertyParserChoices implements PropertyChoices {

    private final PropertyParser propertyParser;

    public PropertyParserChoices(PropertyParser propertyParser) {
        this.propertyParser = propertyParser;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Choices apply(Choices currentChoices, Options options) {
        if (options.isWithDefaults()) {
            return currentChoices;
        }

        return new BasicChoices(
            propertyParser.getLongProperty("smt.waiting.timeout.duration", currentChoices.getTimeoutDuration()),
            propertyParser.getEnumProperty("smt.waiting.timeout.unit", currentChoices.getTimeoutUnit()),
            propertyParser.getLongProperty("smt.waiting.interval.duration", currentChoices.getIntervalDuration()),
            propertyParser.getEnumProperty("smt.waiting.interval.unit", currentChoices.getIntervalUnit()),
            propertyParser.getBooleanProperty("smt.waiting.waitForTrue", currentChoices.isWaitForTrue()),
            propertyParser.getBooleanProperty("smt.waiting.waitForNotNull", currentChoices.isWaitForNotNull()),
            propertyParser.getInstanceProperty("smt.waiting.waitFor", currentChoices.getResultValidators()),
            toSet(propertyParser.getClassProperty("smt.waiting.includes", toList(currentChoices.getIncludes()))),
            toSet(propertyParser.getClassProperty("smt.waiting.excludes", toList(currentChoices.getExcludes())
            ))
        );
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
