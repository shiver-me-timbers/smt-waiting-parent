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

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class MergingManualChoicesTest {

    private Choices currentChoices;
    private Choices manualChoices;
    private MergingManualChoices mergingManualChoices;

    @Before
    public void setUp() {
        currentChoices = mock(Choices.class);
        manualChoices = mock(Choices.class);
        mergingManualChoices = new MergingManualChoices();
    }

    @Test
    public void Can_apply_no_manual_options() {

        final Long timeoutDuration = someLong();
        final TimeUnit timeoutUnit = someEnum(TimeUnit.class);
        final Long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);
        final Boolean waitForTrue = someBoolean();
        final Boolean waitForNotNull = someBoolean();
        final List<ResultValidator> currentValidators = spy(new ArrayList<ResultValidator>());
        final Set<Class<? extends Throwable>> currentIncludes = spy(new HashSet<Class<? extends Throwable>>());
        final Set<Class<? extends Throwable>> currentExcludes = spy(new HashSet<Class<? extends Throwable>>());
        final List<ResultValidator> manualValidators = spy(new ArrayList<ResultValidator>());
        final Set<Class<? extends Throwable>> manualIncludes = spy(new HashSet<Class<? extends Throwable>>());
        final Set<Class<? extends Throwable>> manualExcludes = spy(new HashSet<Class<? extends Throwable>>());

        // Given
        given(currentChoices.getTimeoutDuration()).willReturn(timeoutDuration);
        given(currentChoices.getTimeoutUnit()).willReturn(timeoutUnit);
        given(currentChoices.getIntervalDuration()).willReturn(intervalDuration);
        given(currentChoices.getIntervalUnit()).willReturn(intervalUnit);
        given(currentChoices.isWaitForTrue()).willReturn(waitForTrue);
        given(currentChoices.isWaitForNotNull()).willReturn(waitForNotNull);
        given(currentChoices.getResultValidators()).willReturn(currentValidators);
        given(currentChoices.getIncludes()).willReturn(currentIncludes);
        given(currentChoices.getExcludes()).willReturn(currentExcludes);
        given(manualChoices.getTimeoutDuration()).willReturn(null);
        given(manualChoices.getTimeoutUnit()).willReturn(null);
        given(manualChoices.getIntervalDuration()).willReturn(null);
        given(manualChoices.getIntervalUnit()).willReturn(null);
        given(manualChoices.isWaitForTrue()).willReturn(null);
        given(manualChoices.isWaitForNotNull()).willReturn(null);
        given(manualChoices.getResultValidators()).willReturn(manualValidators);
        given(manualChoices.getIncludes()).willReturn(manualIncludes);
        given(manualChoices.getExcludes()).willReturn(manualExcludes);

        // When
        final Choices actual = mergingManualChoices.apply(currentChoices, manualChoices);

        // Then
        verify(currentValidators).addAll(manualValidators);
        verify(currentIncludes).addAll(manualIncludes);
        verify(currentExcludes).addAll(manualExcludes);
        assertThat(actual.getTimeoutDuration(), equalTo(timeoutDuration));
        assertThat(actual.getTimeoutUnit(), equalTo(timeoutUnit));
        assertThat(actual.getIntervalDuration(), equalTo(intervalDuration));
        assertThat(actual.getIntervalUnit(), equalTo(intervalUnit));
        assertThat(actual.isWaitForTrue(), equalTo(waitForTrue));
        assertThat(actual.isWaitForNotNull(), equalTo(waitForNotNull));
        assertThat(actual.getResultValidators(), equalTo(currentValidators));
    }

    @Test
    public void Can_apply_all_manual_options() {

        final Long timeoutDuration = someLong();
        final TimeUnit timeoutUnit = someEnum(TimeUnit.class);
        final Long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);
        final Boolean waitForTrue = someBoolean();
        final Boolean waitForNotNull = someBoolean();
        final List<ResultValidator> currentValidators = spy(new ArrayList<ResultValidator>());
        final Set<Class<? extends Throwable>> currentIncludes = spy(new HashSet<Class<? extends Throwable>>());
        final Set<Class<? extends Throwable>> currentExcludes = spy(new HashSet<Class<? extends Throwable>>());
        final List<ResultValidator> manualValidators = spy(new ArrayList<ResultValidator>());
        final Set<Class<? extends Throwable>> manualIncludes = spy(new HashSet<Class<? extends Throwable>>());
        final Set<Class<? extends Throwable>> manualExcludes = spy(new HashSet<Class<? extends Throwable>>());

        // Given
        given(currentChoices.getTimeoutDuration()).willReturn(someLong());
        given(currentChoices.getTimeoutUnit()).willReturn(someEnum(TimeUnit.class));
        given(currentChoices.getIntervalDuration()).willReturn(someLong());
        given(currentChoices.getIntervalUnit()).willReturn(someEnum(TimeUnit.class));
        given(currentChoices.isWaitForTrue()).willReturn(someBoolean());
        given(currentChoices.isWaitForNotNull()).willReturn(someBoolean());
        given(currentChoices.getResultValidators()).willReturn(currentValidators);
        given(currentChoices.getIncludes()).willReturn(currentIncludes);
        given(currentChoices.getExcludes()).willReturn(currentExcludes);
        given(manualChoices.getTimeoutDuration()).willReturn(timeoutDuration);
        given(manualChoices.getTimeoutUnit()).willReturn(timeoutUnit);
        given(manualChoices.getIntervalDuration()).willReturn(intervalDuration);
        given(manualChoices.getIntervalUnit()).willReturn(intervalUnit);
        given(manualChoices.isWaitForTrue()).willReturn(waitForTrue);
        given(manualChoices.isWaitForNotNull()).willReturn(waitForNotNull);
        given(manualChoices.getResultValidators()).willReturn(manualValidators);
        given(manualChoices.getIncludes()).willReturn(manualIncludes);
        given(manualChoices.getExcludes()).willReturn(manualExcludes);

        // When
        final Choices actual = mergingManualChoices.apply(currentChoices, manualChoices);

        // Then
        verify(currentValidators).addAll(manualValidators);
        verify(currentIncludes).addAll(manualIncludes);
        verify(currentExcludes).addAll(manualExcludes);
        assertThat(actual.getTimeoutDuration(), equalTo(timeoutDuration));
        assertThat(actual.getTimeoutUnit(), equalTo(timeoutUnit));
        assertThat(actual.getIntervalDuration(), equalTo(intervalDuration));
        assertThat(actual.getIntervalUnit(), equalTo(intervalUnit));
        assertThat(actual.isWaitForTrue(), equalTo(waitForTrue));
        assertThat(actual.isWaitForNotNull(), equalTo(waitForNotNull));
        assertThat(actual.getResultValidators(), equalTo(currentValidators));
    }
}