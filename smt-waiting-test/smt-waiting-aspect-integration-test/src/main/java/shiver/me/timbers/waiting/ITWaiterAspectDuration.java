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
import org.junit.rules.ExpectedException;

import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.waiting.WasDurationMatcher.durationWas;

public abstract class ITWaiterAspectDuration {

    protected abstract ExpectedException expectedException();

    protected abstract WaitingDurationComponent durationComponent();

    @Test
    public void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable {

        final TestTimeOutException exception = new TestTimeOutException();

        expectedException().expect(WaitedTooLongException.class);
        expectedException().expectMessage(containsString("Waited too long for"));
        expectedException().expectCause(is(exception));
        expectedException().expect(durationWas(500, MILLISECONDS));

        final Callable callable = mock(Callable.class);

        // Given
        given(callable.call()).willThrow(exception);

        // When
        durationComponent().durationSetMethod(callable);
    }

    @Test
    public void Can_directly_throw_a_runtime_exception() throws Throwable {

        final TestTimeOutRuntimeException exception = new TestTimeOutRuntimeException();

        expectedException().expect(is(exception));
        expectedException().expect(durationWas(500, MILLISECONDS));

        final Callable callable = mock(Callable.class);

        // Given
        given(callable.call()).willThrow(exception);

        // When
        durationComponent().durationSetMethod(callable);
    }

    @Test
    public void Can_directly_throw_an_error() throws Throwable {

        final TestTimeOutError error = new TestTimeOutError();

        expectedException().expect(is(error));
        expectedException().expect(durationWas(500, MILLISECONDS));

        final Callable callable = mock(Callable.class);

        // Given
        given(callable.call()).willThrow(error);

        // When
        durationComponent().durationSetMethod(callable);
    }
}
