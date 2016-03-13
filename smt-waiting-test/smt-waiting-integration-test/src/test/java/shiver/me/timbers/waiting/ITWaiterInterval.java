/*
 * Copyright 2016 Karl Bennett
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

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ITWaiterInterval {

    private Options options;

    @Before
    public void setUp() {
        options = new Options().withTimeout(500L, MILLISECONDS);
    }

    @Test
    public void Can_change_the_interval() throws Throwable {

        final Until until = mock(Until.class);
        final long start = System.currentTimeMillis();

        // Given
        options.withInterval(200L, MILLISECONDS);
        given(until.success()).willThrow(new Exception()).willReturn(new Object());

        // When
        new Waiter(options).wait(until);

        // Then
        assertThat(System.currentTimeMillis() - start, allOf(greaterThanOrEqualTo(200L), lessThan(300L)));
    }
}
