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
    public Choices apply(Choices choices) {
        return new BasicChoices(
            propertyParser.getLongProperty("smt.waiting.timeout.duration", choices.getTimeoutDuration()),
            propertyParser.getEnumProperty("smt.waiting.timeout.unit", choices.getTimeoutUnit()),
            propertyParser.getLongProperty("smt.waiting.interval.duration", choices.getIntervalDuration()),
            propertyParser.getEnumProperty("smt.waiting.interval.unit", choices.getIntervalUnit()),
            propertyParser.getBooleanProperty("smt.waiting.waitForTrue", choices.isWaitForTrue()),
            propertyParser.getBooleanProperty("smt.waiting.waitForNotNull", choices.isWaitForNotNull()),
            propertyParser.getInstanceProperty("smt.waiting.waitFor", choices.getResultValidators()),
            new HashSet<>(propertyParser.getClassProperty(
                "smt.waiting.include",
                new ArrayList<>((Set) choices.getIncludes())
            )),
            new HashSet<>(propertyParser.getClassProperty(
                "smt.waiting.exclude",
                new ArrayList<>((Set) choices.getExcludes())
            ))
        );
    }
}
