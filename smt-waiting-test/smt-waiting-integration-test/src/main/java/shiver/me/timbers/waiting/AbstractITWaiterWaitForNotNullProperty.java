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
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.waiting.WaitingProperties.addTimeout;

public abstract class AbstractITWaiterWaitForNotNullProperty extends AbstractITWaiterWaitForNotNull implements ITWaiterDefaults {

    @Rule
    public final PropertyRule properties = new PropertyRule();

    @Override
    public WaitingForNotNull waitForNotNull(final long duration, final TimeUnit unit, final boolean isNotNull) {
        return new WaitingForNotNull() {
            @Override
            public <T> T waitForNotNullMethod(Callable<T> callable) throws Exception {
                addTimeout(properties, duration, unit);
                properties.setProperty("smt.waiting.waitForNotNull", String.valueOf(isNotNull));
                return defaults().defaultsMethod(callable);
            }
        };
    }

    protected abstract WaitingForNotNull overrideWaitForNotNull(long duration, TimeUnit unit, boolean isNotNull);

    @Test
    public void Can_override_the_wait_for_not_null_system_property() throws Throwable {

        final Callable callable = mock(Callable.class);

        // Given
        properties.setProperty("smt.waiting.waitForNotNull", "true");
        given(callable.call()).willReturn(null);

        // When
        final Object actual = overrideWaitForNotNull(500L, MILLISECONDS, false).waitForNotNullMethod(callable);

        // Then
        assertThat(actual, nullValue());
        verify(callable).call();
    }
}
