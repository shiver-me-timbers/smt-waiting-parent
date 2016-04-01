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

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractITWaiter implements ITWaiter {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Override
    public ExpectedException expectedException() {
        return expectedException;
    }

    private final AbstractITWaiterInterval interval = new AbstractITWaiterInterval() {
        @Override
        public WaitingInterval interval(long duration, TimeUnit unit) {
            return AbstractITWaiter.this.interval(duration, unit);
        }
    };

    private final AbstractITWaiterTimeout timeout = new AbstractITWaiterTimeout() {
        @Override
        public ExpectedException expectedException() {
            return AbstractITWaiter.this.expectedException();
        }

        @Override
        public WaitingTimeout timeout(long duration, TimeUnit unit) {
            return AbstractITWaiter.this.timeout(duration, unit);
        }
    };

    private final AbstractITWaiterWaitFor waitFor = new AbstractITWaiterWaitFor() {
        @Override
        public WaitingFor waitFor(long duration, TimeUnit unit, ResultValidator validator) {
            return AbstractITWaiter.this.waitFor(duration, unit, validator);
        }
    };

    private final AbstractITWaiterWaitForNotNull waitForNotNull = new AbstractITWaiterWaitForNotNull() {
        @Override
        public WaitingForNotNull waitForNotNull(long duration, TimeUnit unit, boolean isNotNull) {
            return AbstractITWaiter.this.waitForNotNull(duration, unit, isNotNull);
        }
    };

    private final AbstractITWaiterWaitForTrue waitForTrue = new AbstractITWaiterWaitForTrue() {
        @Override
        public WaitingForTrue waitForTrue(long duration, TimeUnit unit, boolean isTrue) {
            return AbstractITWaiter.this.waitForTrue(duration, unit, isTrue);
        }
    };

    private final AbstractITWaiterInclude include = new AbstractITWaiterInclude() {
        @Override
        public ExpectedException expectedException() {
            return AbstractITWaiter.this.expectedException();
        }

        @Override
        public WaitingInclude include(long duration, TimeUnit unit, Throwable... includes) {
            return AbstractITWaiter.this.include(duration, unit, includes);
        }

        @Override
        public WaitingInclude includeWithExclude(
            long duration,
            TimeUnit unit,
            List<Throwable> includes,
            List<Throwable> excludes
        ) {
            return AbstractITWaiter.this.includeWithExclude(duration, unit, includes, excludes);

        }
    };

    @Test
    @Override
    public void Can_change_the_interval() throws Throwable {
        interval.Can_change_the_interval();
    }

    @Test
    @Override
    public void Can_change_the_timeout() throws Throwable {
        timeout.Can_change_the_timeout();
    }

    @Test
    @Override
    public void Can_wait_until_no_exception_is_thrown() throws Throwable {
        timeout.Can_wait_until_no_exception_is_thrown();
    }

    @Test
    @Override
    public void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable {
        timeout.Can_wait_until_time_out_if_exception_always_thrown();
    }

    @Test
    @Override
    public void Can_directly_throw_a_runtime_exception() throws Throwable {
        timeout.Can_directly_throw_a_runtime_exception();
    }

    @Test
    @Override
    public void Can_directly_throw_an_error() throws Throwable {
        timeout.Can_directly_throw_an_error();
    }

    @Test
    @Override
    public void Can_wait_until_valid_result_is_returned() throws Throwable {
        waitFor.Can_wait_until_valid_result_is_returned();
    }

    @Test
    @Override
    public void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned() throws Throwable {
        waitFor.Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned();
    }

    @Test
    @Override
    public void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned_and_an_exception_was_thrown() throws Throwable {
        waitFor.Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned_and_an_exception_was_thrown();
    }

    @Test
    @Override
    public void Can_wait_for_a_non_null_value() throws Throwable {
        waitForNotNull.Can_wait_for_a_non_null_value();
    }

    @Test
    @Override
    public void Can_wait_until_time_out_for_non_null_when_null_always_returned() throws Throwable {
        waitForNotNull.Can_wait_until_time_out_for_non_null_when_null_always_returned();
    }

    @Test
    @Override
    public void Can_wait_until_true_is_returned() throws Throwable {
        waitForTrue.Can_wait_until_true_is_returned();
    }

    @Test
    @Override
    public void Can_wait_until_time_out_for_true_when_false_always_returned() throws Throwable {
        waitForTrue.Can_wait_until_time_out_for_true_when_false_always_returned();
    }

    @Test
    @Override
    public void Can_wait_until_time_out_for_true_when_null_always_returned() throws Throwable {
        waitForTrue.Can_wait_until_time_out_for_true_when_null_always_returned();
    }

    @Test
    @Override
    public void Can_ignore_exceptions_contained_in_the_include_list() throws Throwable {
        include.Can_ignore_exceptions_contained_in_the_include_list();
    }

    @Test
    @Override
    public void Cannot_ignore_exceptions_that_are_not_contained_in_the_include_list() throws Throwable {
        include.Cannot_ignore_exceptions_that_are_not_contained_in_the_include_list();
    }

    @Test
    @Override
    public void Can_ignore_all_exceptions_if_no_includes_set() throws Throwable {
        include.Can_ignore_all_exceptions_if_no_includes_set();
    }

    @Test
    @Override
    public void Can_ignore_exceptions_contained_in_the_include_list_and_not_in_the_exclude_list() throws Throwable {
        include.Can_ignore_exceptions_contained_in_the_include_list_and_not_in_the_exclude_list();
    }
}
