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

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.waiting.HasFieldMatcher.hasField;

public class BasicChooserTest {

    private Sleeper sleeper;
    private BasicChooser chooser;

    @Before
    public void setUp() {
        sleeper = mock(Sleeper.class);
        chooser = new BasicChooser(sleeper);
    }

    @Test
    public void Can_choose_all_choice() {

        final Choices choices = mock(Choices.class);

        final Long timeoutDuration = someLong();
        final TimeUnit timeoutUnit = someEnum(TimeUnit.class);
        final Long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);
        @SuppressWarnings("unchecked")
        final List<ResultValidator> resultValidators = mock(List.class);

        // Given
        given(choices.getTimeoutDuration()).willReturn(timeoutDuration);
        given(choices.getTimeoutUnit()).willReturn(timeoutUnit);
        given(choices.getIntervalDuration()).willReturn(intervalDuration);
        given(choices.getIntervalUnit()).willReturn(intervalUnit);
        given(choices.isWaitForTrue()).willReturn(true);
        given(choices.isWaitForNotNull()).willReturn(true);
        given(choices.getResultValidators()).willReturn(resultValidators);

        // When
        final Choice actual = chooser.choose(choices);

        // Then
        verify(resultValidators, times(2)).add(any(ResultValidator.class));
        assertThat(actual, hasField("sleeper", sleeper));
        assertThat(actual, hasField("timeoutDuration", timeoutDuration));
        assertThat(actual, hasField("timeoutUnit", timeoutUnit));
        assertThat(actual, hasField("resultValidators", resultValidators));
        assertThat(actual, hasField("intervalDuration", intervalDuration));
        assertThat(actual, hasField("intervalUnit", intervalUnit));
    }

    @Test
    public void Can_not_add_tru_and_null_result_validators() {

        final Choices choices = mock(Choices.class);

        @SuppressWarnings("unchecked")
        final List<ResultValidator> resultValidators = mock(List.class);

        // Given
        given(choices.getTimeoutDuration()).willReturn(someLong());
        given(choices.getTimeoutUnit()).willReturn(someEnum(TimeUnit.class));
        given(choices.getIntervalDuration()).willReturn(someLong());
        given(choices.getIntervalUnit()).willReturn(someEnum(TimeUnit.class));
        given(choices.isWaitForTrue()).willReturn(false);
        given(choices.isWaitForNotNull()).willReturn(false);
        given(choices.getResultValidators()).willReturn(resultValidators);

        // When
        chooser.choose(choices);

        // Then
        verifyZeroInteractions(resultValidators);
    }
}