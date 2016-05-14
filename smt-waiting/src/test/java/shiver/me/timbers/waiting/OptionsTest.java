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

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.waiting.RandomExceptions.someThrowable;

public class OptionsTest {

    private DefaultChoices defaultChoices;
    private PropertyChoices propertyChoices;
    private ManualChoices manualChoices;
    private Chooser chooser;
    private OptionsService options;

    @Before
    public void setUp() {
        defaultChoices = mock(DefaultChoices.class);
        propertyChoices = mock(PropertyChoices.class);
        manualChoices = mock(ManualChoices.class);
        chooser = mock(Chooser.class);
        options = new Options(defaultChoices, propertyChoices, manualChoices, chooser);
    }

    @Test
    public void Can_create_a_default_options() {

        // When
        new Options();
    }

    @Test
    public void Nothing_is_set_on_creation() throws InterruptedException {

        // When
        new Options(defaultChoices, propertyChoices, manualChoices, chooser);

        // Then
        verifyZeroInteractions(defaultChoices, propertyChoices, manualChoices, chooser);
    }

    @Test
    public void Can_create_a_choice() throws InterruptedException {

        final Choices defaults = mock(Choices.class);
        final Choices properties = mock(Choices.class);
        final Choices manual = mock(Choices.class);

        final Choice expected = mock(Choice.class);

        // Given
        given(defaultChoices.create()).willReturn(defaults);
        given(propertyChoices.apply(defaults, (Options) options)).willReturn(properties);
        given(manualChoices.apply(properties, options)).willReturn(manual);
        given(chooser.choose(manual)).willReturn(expected);

        // When
        final Choice actual = options.choose();

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_set_options() {

        // Given
        final Long timeoutDuration = someLong();
        final TimeUnit timeoutUnit = someEnum(TimeUnit.class);
        final Long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);
        final Boolean shouldWaitForTrue = someBoolean();
        final Boolean shouldWaitForNotNull = someBoolean();
        final ResultValidator validator1 = mock(ResultValidator.class);
        final ResultValidator validator2 = mock(ResultValidator.class);
        final Boolean clearWaitFor = someBoolean();
        final Class<? extends Throwable> throwable1 = someThrowable().getClass();
        final Class<? extends Throwable> throwable2 = someThrowable().getClass();
        final Boolean clearIncludes = someBoolean();
        final Class<? extends Throwable> throwable3 = someThrowable().getClass();
        final Class<? extends Throwable> throwable4 = someThrowable().getClass();
        final Boolean clearExcludes = someBoolean();
        final Boolean useDefaults = someBoolean();

        // When
        options.withTimeout(timeoutDuration, timeoutUnit)
            .withInterval(intervalDuration, intervalUnit)
            .willWaitForTrue(shouldWaitForTrue)
            .willWaitForNotNull(shouldWaitForNotNull)
            .waitFor(validator1, validator2)
            .clearWaitFor(clearWaitFor)
            .includes(throwable1, throwable2)
            .clearIncludes(clearIncludes)
            .excludes(throwable3, throwable4)
            .clearExcludes(clearExcludes)
            .withDefaults(useDefaults);

        // Then
        assertThat(options.getTimeoutDuration(), is(timeoutDuration));
        assertThat(options.getTimeoutUnit(), is(timeoutUnit));
        assertThat(options.getIntervalDuration(), is(intervalDuration));
        assertThat(options.getIntervalUnit(), is(intervalUnit));
        assertThat(options.isWaitForTrue(), is(shouldWaitForTrue));
        assertThat(options.isWaitForNotNull(), is(shouldWaitForNotNull));
        assertThat(options.getResultValidators(), contains(
            instanceOf(validator1.getClass()), instanceOf(validator2.getClass())
        ));
        assertThat(((Options) options).isClearWaitFor(), is(clearWaitFor));
        assertThat(options.getIncludes(), (Matcher) hasItems(
            instanceOf(throwable1.getClass()), instanceOf(throwable1.getClass())
        ));
        assertThat(((Options) options).isClearIncludes(), is(clearIncludes));
        assertThat(options.getExcludes(), (Matcher) hasItems(
            instanceOf(throwable3.getClass()), instanceOf(throwable4.getClass())
        ));
        assertThat(((Options) options).isClearExcludes(), is(clearExcludes));
        assertThat(((Options) options).isWithDefaults(), is(useDefaults));
    }
}