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

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class PropertyParserChoicesTest {

    @Test
    @SuppressWarnings("unchecked")
    public void Can_create_a_system_property_choices() {

        final PropertyParser propertyParser = mock(PropertyParser.class);

        final Choices choices = mock(Choices.class);

        final Long previousTimeoutDuration = someLong();
        final TimeUnit previousTimeoutUnit = someEnum(TimeUnit.class);
        final Long previousIntervalDuration = someLong();
        final TimeUnit previousIntervalUnit = someEnum(TimeUnit.class);
        final Boolean previousWaitForTrue = someBoolean();
        final Boolean previousWaitForNotNull = someBoolean();
        final List<ResultValidator> previousResultValidators = emptyList();
        final long timeoutDuration = someLong();
        final TimeUnit timeoutUnit = someEnum(TimeUnit.class);
        final long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);
        final Boolean waitForTrue = someBoolean();
        final Boolean waitForNotNull = someBoolean();
        final List<ResultValidator> previousValidators = spy(new ArrayList<ResultValidator>());
        final List<ResultValidator> propertyValidators = spy(new ArrayList<ResultValidator>());
        final List<Class<? extends Throwable>> previousIncludes = spy(new ArrayList<Class<? extends Throwable>>());
        final List<Class<? extends Throwable>> propertyIncludes = spy(new ArrayList<Class<? extends Throwable>>());
        final List<Class<? extends Throwable>> previousExcludes = spy(new ArrayList<Class<? extends Throwable>>());
        final List<Class<? extends Throwable>> propertyExcludes = spy(new ArrayList<Class<? extends Throwable>>());

        // Given
        given(choices.getTimeoutDuration()).willReturn(previousTimeoutDuration);
        given(choices.getTimeoutUnit()).willReturn(previousTimeoutUnit);
        given(choices.getIntervalDuration()).willReturn(previousIntervalDuration);
        given(choices.getIntervalUnit()).willReturn(previousIntervalUnit);
        given(choices.isWaitForTrue()).willReturn(previousWaitForTrue);
        given(choices.isWaitForNotNull()).willReturn(previousWaitForNotNull);
        given(choices.getResultValidators()).willReturn(previousResultValidators);
        given(propertyParser.getLongProperty("smt.waiting.timeout.duration", previousTimeoutDuration))
            .willReturn(timeoutDuration);
        given(propertyParser.getEnumProperty("smt.waiting.timeout.unit", previousTimeoutUnit)).willReturn(timeoutUnit);
        given(propertyParser.getLongProperty("smt.waiting.interval.duration", previousIntervalDuration))
            .willReturn(intervalDuration);
        given(propertyParser.getEnumProperty("smt.waiting.interval.unit", previousIntervalUnit))
            .willReturn(intervalUnit);
        given(propertyParser.getBooleanProperty("smt.waiting.waitForTrue", previousWaitForTrue))
            .willReturn(waitForTrue);
        given(propertyParser.getBooleanProperty("smt.waiting.waitForNotNull", previousWaitForNotNull))
            .willReturn(waitForNotNull);
        given(propertyParser.getInstanceProperty("smt.waiting.waitFor", previousValidators))
            .willReturn((List) propertyValidators);
        given(propertyParser.getClassProperty("smt.waiting.includes", (List) previousIncludes))
            .willReturn((List) propertyIncludes);
        given(propertyParser.getClassProperty("smt.waiting.excludes", (List) previousExcludes))
            .willReturn((List) propertyExcludes);

        // When
        final Choices actual = new PropertyParserChoices(propertyParser).apply(choices);

        // Then
        assertThat(actual.getTimeoutDuration(), is(timeoutDuration));
        assertThat(actual.getTimeoutUnit(), is(timeoutUnit));
        assertThat(actual.getIntervalDuration(), is(intervalDuration));
        assertThat(actual.getIntervalUnit(), is(intervalUnit));
        assertThat(actual.isWaitForTrue(), is(waitForTrue));
        assertThat(actual.isWaitForNotNull(), is(waitForNotNull));
        assertThat(actual.getResultValidators(), is(propertyValidators));
        assertThat(actual.getIncludes(), equalTo((Set) new HashSet<>(propertyIncludes)));
        assertThat(actual.getExcludes(), equalTo((Set) new HashSet<>(propertyExcludes)));
    }
}