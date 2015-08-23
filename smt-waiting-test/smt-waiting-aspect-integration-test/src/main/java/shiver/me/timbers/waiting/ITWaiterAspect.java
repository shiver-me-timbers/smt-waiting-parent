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

    private final ITWaiterAspectDuration duration = new ITWaiterAspectDuration() {
        @Override
        protected ExpectedException expectedException() {
            return expectedException;
        }

        @Override
        protected WaitingDurationComponent durationComponent() {
            return ITWaiterAspect.this.durationComponent();
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

    private final ITWaiterAspectInterval interval = new ITWaiterAspectInterval() {
        @Override
        protected WaitingIntervalComponent intervalComponent() {
            return ITWaiterAspect.this.intervalComponent();
        }
    };

    protected abstract WaitingDefaultsComponent defaultsComponent();

    protected abstract WaitingDurationComponent durationComponent();

    protected abstract WaitingForComponent waitForComponent();

    protected abstract WaitingForTrueComponent waitForTrueComponent();

    protected abstract WaitingForNotNullComponent waitForNotNullComponent();

    protected abstract WaitingIntervalComponent intervalComponent();

    @Test
    public void Can_wait_until_no_exception_is_thrown() throws Exception {

        defaults.Can_wait_until_no_exception_is_thrown();
    }

    @Test
    public void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable {

        duration.Can_wait_until_time_out_if_exception_always_thrown();
    }

    @Test
    public void Can_directly_throw_a_runtime_exception() throws Throwable {

        duration.Can_directly_throw_a_runtime_exception();
    }

    @Test
    public void Can_directly_throw_an_error() throws Throwable {

        duration.Can_directly_throw_an_error();
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
}
