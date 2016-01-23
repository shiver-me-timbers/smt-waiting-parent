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

public abstract class ITWaiterAspect {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final ITWaiterAspectDefaults defaults = new ITWaiterAspectDefaults() {
        @Override
        protected WaitingDefaultsComponent defaultsComponent() {
            return ITWaiterAspect.this.defaultsComponent();
        }
    };

    private final ITWaiterAspectTimeout timeout = new ITWaiterAspectTimeout() {
        @Override
        protected ExpectedException expectedException() {
            return expectedException;
        }

        @Override
        protected WaitingTimeoutComponent durationComponent() {
            return ITWaiterAspect.this.timeoutComponent();
        }
    };

    private final ITWaiterAspectInterval interval = new ITWaiterAspectInterval() {
        @Override
        protected WaitingIntervalComponent intervalComponent() {
            return ITWaiterAspect.this.intervalComponent();
        }
    };

    private final ITWaiterAspectWaitFor waitFor = new ITWaiterAspectWaitFor() {
        @Override
        protected WaitingForComponent waitForComponent() {
            return ITWaiterAspect.this.waitForComponent();
        }
    };

    private final ITWaiterAspectWaitForTrue waitForTrue = new ITWaiterAspectWaitForTrue() {
        @Override
        protected WaitingForTrueComponent waitForTrueComponent() {
            return ITWaiterAspect.this.waitForTrueComponent();
        }
    };

    private final ITWaiterAspectWaitForNotNull waitForNotNull = new ITWaiterAspectWaitForNotNull() {
        @Override
        protected WaitingForNotNullComponent waitForNotNullComponent() {
            return ITWaiterAspect.this.waitForNotNullComponent();
        }
    };

    private final ITWaiterAspectInclude include = new ITWaiterAspectInclude() {
        @Override
        protected ExpectedException expectedException() {
            return expectedException;
        }

        @Override
        protected WaitingIncludeComponent includeComponent() {
            return ITWaiterAspect.this.includeComponent();
        }
    };

    private final ITWaiterAspectExclude exclude = new ITWaiterAspectExclude() {
        @Override
        protected ExpectedException expectedException() {
            return expectedException;
        }

        @Override
        protected WaitingExcludeComponent excludeComponent() {
            return ITWaiterAspect.this.excludeComponent();
        }
    };

    protected abstract WaitingDefaultsComponent defaultsComponent();

    protected abstract WaitingTimeoutComponent timeoutComponent();

    protected abstract WaitingForComponent waitForComponent();

    protected abstract WaitingForTrueComponent waitForTrueComponent();

    protected abstract WaitingForNotNullComponent waitForNotNullComponent();

    protected abstract WaitingIntervalComponent intervalComponent();

    protected abstract WaitingIncludeComponent includeComponent();

    protected abstract WaitingExcludeComponent excludeComponent();

    @Test
    public void Can_wait_until_no_exception_is_thrown() throws Exception {
        defaults.Can_wait_until_no_exception_is_thrown();
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
    public void Can_wait_until_true_is_returned() throws Throwable {
        waitForTrue.Can_wait_until_true_is_returned();
    }

    @Test
    public void Can_wait_until_time_out_for_true_when_false_always_returned() throws Throwable {
        waitForTrue.Can_wait_until_time_out_for_true_when_false_always_returned();
    }

    @Test
    public void Can_wait_until_time_out_for_true_when_null_always_returned() throws Throwable {
        waitForTrue.Can_wait_until_time_out_for_true_when_null_always_returned();
    }

    @Test
    public void Can_wait_for_a_non_null_value() throws Throwable {
        waitForNotNull.Can_wait_for_a_non_null_value();
    }

    @Test
    public void Can_wait_until_time_out_for_non_null_when_null_always_returned() throws Throwable {
        waitForNotNull.Can_wait_until_time_out_for_non_null_when_null_always_returned();
    }

    @Test
    public void Can_change_the_interval() throws Throwable {
        interval.Can_change_the_interval();
    }

    @Test
    public void Can_ignore_exceptions_contained_in_the_include_list() throws Throwable {
        include.Can_ignore_exceptions_contained_in_the_include_list();
    }

    @Test
    public void Cannot_ignore_exceptions_that_are_not_contained_in_the_include_list() throws Throwable {
        include.Cannot_ignore_exceptions_that_are_not_contained_in_the_include_list();
    }

    @Test
    public void Cannot_ignore_exceptions_contained_in_the_exclude_list() throws Throwable {
        exclude.Cannot_ignore_exceptions_contained_in_the_exclude_list();
    }

    @Test
    public void Can_ignore_exceptions_that_are_not_contained_in_the_exclude_list() throws Throwable {
        exclude.Can_ignore_exceptions_that_are_not_contained_in_the_exclude_list();
    }
}
