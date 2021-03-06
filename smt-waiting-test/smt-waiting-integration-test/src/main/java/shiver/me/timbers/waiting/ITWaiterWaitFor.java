/*
 * Copyright 2016 Karl Bennett
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

import shiver.me.timbers.waiting.execution.WaitingFor;

import java.util.concurrent.TimeUnit;

public interface ITWaiterWaitFor {

    WaitingFor waitFor(long duration, TimeUnit unit, ResultValidator validator);

    void Can_wait_until_valid_result_is_returned() throws Throwable;

    void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned() throws Throwable;

    void Can_wait_until_time_out_for_valid_result_when_an_invalid_result_is_always_returned_and_an_exception_was_thrown() throws Throwable;
}
