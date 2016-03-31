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

import java.util.concurrent.TimeUnit;

public interface ITWaiterTimeout {

    WaitingTimeout timeout(long duration, TimeUnit unit);

    void Can_change_the_timeout() throws Throwable;

    void Can_wait_until_no_exception_is_thrown() throws Throwable;

    void Can_wait_until_time_out_if_exception_always_thrown() throws Throwable;

    void Can_directly_throw_a_runtime_exception() throws Throwable;

    void Can_directly_throw_an_error() throws Throwable;
}
