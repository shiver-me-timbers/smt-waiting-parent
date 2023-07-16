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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NotNullResultTest {

    @Test
    public void Can_check_for_a_not_null_result() throws Throwable {

        // When
        final boolean actual = new NotNullResult().isValid(new Object());

        // Then
        assertThat(actual, is(true));
    }

    @Test
    public void Can_check_for_a_null_result() throws Throwable {

        // When
        final boolean actual = new NotNullResult().isValid(null);

        // Then
        assertThat(actual, is(false));
    }
}