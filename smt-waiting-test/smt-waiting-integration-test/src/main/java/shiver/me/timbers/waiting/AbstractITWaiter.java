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
import org.junit.rules.ExpectedException;

import java.util.concurrent.TimeUnit;

public abstract class AbstractITWaiter implements ITWaiter {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final AbstractITWaiterInterval interval = new AbstractITWaiterInterval() {
        @Override
        public WaitingInterval interval(long duration, TimeUnit unit) {
            return AbstractITWaiter.this.interval(duration, unit);
        }
    };

    private final AbstractITWaiterTimeout timeout = new AbstractITWaiterTimeout() {
        @Override
        protected ExpectedException expectedException() {
            return expectedException;
        }

        @Override
        public WaiterTimeout timeout(long duration, TimeUnit unit) {
            return AbstractITWaiter.this.timeout(duration, unit);
        }
    };

    private final AbstractITWaiterWaitFor waitFor = new AbstractITWaiterWaitFor() {
        @Override
        public WaitingFor waitFor(long duration, TimeUnit unit, ResultValidator validator) {
            return AbstractITWaiter.this.waitFor(duration, unit, validator);
        }
    };

    @Test
    public void Can_change_the_interval() throws Throwable {
        interval.Can_change_the_interval();
    }

    @Test
    public void Can_change_the_timeout() throws Throwable {
        timeout.Can_change_the_timeout();
    }

    @Test
    public void Can_wait_until_no_exception_is_thrown() throws Throwable {
        timeout.Can_wait_until_no_exception_is_thrown();
    }

    @Test
    public void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable {
        timeout.Can_wait_until_time_out_if_exception_always_thrown();
    }

    @Test
    public void Can_directly_throw_a_runtime_exception() throws Throwable {
        timeout.Can_directly_throw_a_runtime_exception();
    }

    @Test
    public void Can_directly_throw_an_error() throws Throwable {
        timeout.Can_directly_throw_an_error();
    }

    @Test
    public void Can_wait_until_valid_result_is_returned() throws Throwable {
        waitFor.Can_wait_until_valid_result_is_returned();
    }

    @Test
    public void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned() throws Throwable {
        waitFor.Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned();
    }

    @Test
    public void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned_and_an_exception_was_thrown() throws Throwable {
        waitFor.Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned_and_an_exception_was_thrown();
    }
}
