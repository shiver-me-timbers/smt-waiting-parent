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

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public abstract class AbstractITWaiterWithDefaults implements ITWaiterWithDefaults {

    @Rule
    public final PropertyRule properties = new PropertyRule();

    @Test
    @Override
    public void Can_reset_values_back_to_defaults() throws Throwable {

        final Callable until = mock(Callable.class);

        final Object expected = new Object();

        // Given
        properties.setProperty("smt.waiting.timeout.duration", "30");
        properties.setProperty("smt.waiting.timeout.unit", "SECONDS");
        properties.setProperty("smt.waiting.interval.duration", "10");
        properties.setProperty("smt.waiting.interval.unit", "SECONDS");
        properties.setProperty("smt.waiting.waitForTrue", "true");
        properties.setProperty("smt.waiting.waitForNotNull", "true");
        properties.setProperty("smt.waiting.waitFor", FailResult.class.getName());
        properties.setProperty("smt.waiting.include", IllegalArgumentException.class.getName());
        properties.setProperty("smt.waiting.exclude", IllegalStateException.class.getName());
        given(until.call()).willThrow(new IllegalStateException()).willReturn(expected);

        // When
        final Object actual = withDefaults(true).defaultsMethod(until);

        // Then
        assertThat(actual, is(expected));
        verify(until, times(2)).call();
    }
}
