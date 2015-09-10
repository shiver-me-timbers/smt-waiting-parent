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

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class OptionsTest {

    private Options options;
    private DefaultChoices defaultChoices;
    private PropertyChoices propertyChoices;
    private ManualChoices manualChoices;
    private Chooser chooser;

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
        verifyZeroInteractions(defaultChoices, propertyChoices, manualChoices);
    }

    @Test
    public void Can_create_a_choice() throws InterruptedException {

        final Choices defaults = mock(Choices.class);
        final Choices properties = mock(Choices.class);
        final Choices manual = mock(Choices.class);

        final Choice expected = mock(Choice.class);

        // Given
        given(defaultChoices.create()).willReturn(defaults);
        given(propertyChoices.apply(defaults)).willReturn(properties);
        given(manualChoices.apply(eq(properties), any(Choices.class))).willReturn(manual);
        given(chooser.choose(manual)).willReturn(expected);

        // When
        final Choice actual = options.choose();

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_create_a_choice_with_custom_values() throws InterruptedException {

        final Choices defaults = mock(Choices.class);
        final Choices properties = mock(Choices.class);
        final ResultValidator resultValidator2 = mock(ResultValidator.class);
        final Choices manual = mock(Choices.class);

        final Choice expected = mock(Choice.class);

        // Given
        given(defaultChoices.create()).willReturn(defaults);
        given(propertyChoices.apply(defaults)).willReturn(properties);
        given(manualChoices.apply(eq(properties), any(Choices.class))).willReturn(manual);
        given(chooser.choose(manual)).willReturn(expected);

        // When
        final Choice actual = options.withTimeOut(someLong(), someEnum(TimeUnit.class))
            .withInterval(someLong(), someEnum(TimeUnit.class))
            .willWaitForTrue(someBoolean())
            .willWaitForNotNull(someBoolean())
            .waitFor(mock(ResultValidator.class))
            .waitFor(resultValidator2)
            .choose();

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_create_a_choice_with_defaults() throws InterruptedException {

        final Choices defaults = mock(Choices.class);
        final Choices manual = mock(Choices.class);

        final Choice expected = mock(Choice.class);

        // Given
        given(defaultChoices.create()).willReturn(defaults);
        given(manualChoices.apply(eq(defaults), any(Choices.class))).willReturn(manual);
        given(chooser.choose(manual)).willReturn(expected);

        // When
        final Choice actual = options.withDefaults(true).choose();

        // Then
        assertThat(actual, is(expected));
        verifyZeroInteractions(propertyChoices);
    }
}