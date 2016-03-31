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
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public abstract class AbstractITWaiterWaitForTrueProperty extends AbstractITWaiterWaitForTrue implements ITWaiterDefaults {

    @Rule
    public final PropertyRule properties = new PropertyRule();

    @Override
    public WaitingForTrue waitForTrue(long duration, TimeUnit unit, boolean isTrue) {
        return new WaitingForTrue() {
            @Override
            public <T> T waitForTrueMethod(Callable<T> callable) throws Exception {
                return defaults().defaultsMethod(callable);
            }
        };
    }

    @Override
    public void Can_wait_until_true_is_returned() throws Throwable {
        properties.setProperty("smt.waiting.timeout.duration", "500");
        properties.setProperty("smt.waiting.timeout.unit", MILLISECONDS.name());
        properties.setProperty("smt.waiting.waitForTrue", "true");
        super.Can_wait_until_true_is_returned();
    }

    @Override
    public void Can_wait_until_time_out_for_true_when_false_always_returned() throws Throwable {
        setShortTimeoutWithWaitForTrue();
        super.Can_wait_until_time_out_for_true_when_false_always_returned();
    }

    @Override
    public void Can_wait_until_time_out_for_true_when_null_always_returned() throws Throwable {
        setShortTimeoutWithWaitForTrue();
        super.Can_wait_until_time_out_for_true_when_null_always_returned();
    }

    private void setShortTimeoutWithWaitForTrue() {
        properties.setProperty("smt.waiting.timeout.duration", "200");
        properties.setProperty("smt.waiting.timeout.unit", MILLISECONDS.name());
        properties.setProperty("smt.waiting.waitForTrue", "true");
    }

    protected abstract WaitingForTrue overrideWaitForTrue(long duration, TimeUnit unit, boolean isTrue);

    @Test
    public void Can_override_the_wait_for_true_system_property() throws Throwable {

        final Callable callable = mock(Callable.class);

        // Given
        properties.setProperty("smt.waiting.waitForTrue", "true");
        given(callable.call()).willReturn(false);

        // When
        final Object actual = overrideWaitForTrue(200L, MILLISECONDS, false).waitForTrueMethod(callable);

        // Then
        assertThat(actual, is((Object) false));
        verify(callable).call();
    }
}
