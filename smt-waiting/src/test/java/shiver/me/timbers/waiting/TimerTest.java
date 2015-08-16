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

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class TimerTest {

    @Test
    public void Can_check_timeout() {

        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);
        final Start start = mock(Start.class);

        final Date end = mock(Date.class);

        final boolean expected = someBoolean();

        // Given
        given(start.add(duration, unit)).willReturn(end);
        given(end.before(any(Date.class))).willReturn(expected);

        // When
        final boolean actual = new Timer(duration, unit, start).exceeded();

        // Then
        assertThat(actual, is(expected));
    }
}