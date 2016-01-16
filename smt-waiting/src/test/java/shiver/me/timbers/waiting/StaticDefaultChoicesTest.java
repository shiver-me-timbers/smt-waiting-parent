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

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StaticDefaultChoicesTest {

    @Test
    public void Can_create_default_choices() {

        // When
        final Choices actual = new StaticDefaultChoices().create();

        // Then
        assertThat(actual.getTimeoutDuration(), is(10L));
        assertThat(actual.getTimeoutUnit(), is(SECONDS));
        assertThat(actual.getIntervalDuration(), is(100L));
        assertThat(actual.getIntervalUnit(), is(MILLISECONDS));
        assertThat(actual.isWaitForTrue(), is(false));
        assertThat(actual.isWaitForNotNull(), is(false));
        assertThat(actual.getResultValidators(), empty());
        assertThat(actual.getIncludes(), empty());
        assertThat(actual.getExcludes(), empty());
    }
}