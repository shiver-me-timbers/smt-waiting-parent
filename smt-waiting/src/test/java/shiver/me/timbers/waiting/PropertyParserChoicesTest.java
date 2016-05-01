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

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.waiting.RandomExceptions.someThrowable;

public class PropertyParserChoicesTest {

    private PropertyParser propertyParser;
    private Choices currentChoices;
    private Options options;
    private PropertyParserChoices choices;

    @Before
    public void setUp() {
        propertyParser = mock(PropertyParser.class);
        currentChoices = mock(Choices.class);
        options = mock(Options.class);
        choices = new PropertyParserChoices(propertyParser);
    }

    @Test
    public void Can_return_the_current_choices_instead_of_the_properties() {

        // Given
        given(options.isWithDefaults()).willReturn(true);

        // When
        final Choices actual = choices.apply(currentChoices, options);

        // Then
        assertThat(actual, is(currentChoices));
    }

    @Test
    public void Can_ignore_any_wait_for_set_through_properties() {

        final List<ResultValidator> validators = singletonList(mock(ResultValidator.class));

        // Given
        given(options.isClearWaitFor()).willReturn(true);
        given(currentChoices.getResultValidators()).willReturn(validators);

        // When
        final Choices actual = choices.apply(currentChoices, options);

        // Then
        assertThat(actual.getResultValidators(), is(validators));
    }

    @Test
    public void Can_ignore_any_includes_set_through_properties() {

        @SuppressWarnings("unchecked")
        final Set<Class<? extends Throwable>> includes = (Set) singleton(someThrowable().getClass());

        // Given
        given(options.isClearIncludes()).willReturn(true);
        given(currentChoices.getIncludes()).willReturn(includes);

        // When
        final Choices actual = choices.apply(currentChoices, options);

        // Then
        assertThat(actual.getIncludes(), is(includes));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_create_a_system_property_choices() {

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
        final List<ResultValidator> previousValidators = new ArrayList<>();
        final List<ResultValidator> propertyValidators = new ArrayList<>();
        final List<Class<? extends Throwable>> previousIncludes = new ArrayList<>();
        final List<Class<? extends Throwable>> propertyIncludes = new ArrayList<>();
        final List<Class<? extends Throwable>> previousExcludes = new ArrayList<>();
        final List<Class<? extends Throwable>> propertyExcludes = new ArrayList<>();

        // Given
        given(options.isWithDefaults()).willReturn(false);
        given(currentChoices.getTimeoutDuration()).willReturn(previousTimeoutDuration);
        given(currentChoices.getTimeoutUnit()).willReturn(previousTimeoutUnit);
        given(currentChoices.getIntervalDuration()).willReturn(previousIntervalDuration);
        given(currentChoices.getIntervalUnit()).willReturn(previousIntervalUnit);
        given(currentChoices.isWaitForTrue()).willReturn(previousWaitForTrue);
        given(currentChoices.isWaitForNotNull()).willReturn(previousWaitForNotNull);
        given(currentChoices.getResultValidators()).willReturn(previousResultValidators);
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
            .willReturn(propertyValidators);
        given(propertyParser.getClassProperty("smt.waiting.includes", (List) previousIncludes))
            .willReturn((List) propertyIncludes);
        given(propertyParser.getClassProperty("smt.waiting.excludes", (List) previousExcludes))
            .willReturn((List) propertyExcludes);

        // When
        final Choices actual = choices.apply(currentChoices, options);

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