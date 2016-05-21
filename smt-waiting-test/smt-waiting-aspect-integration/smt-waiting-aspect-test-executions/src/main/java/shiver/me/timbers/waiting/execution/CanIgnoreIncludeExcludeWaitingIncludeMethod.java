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

package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Wait;

import java.util.concurrent.Callable;

public class CanIgnoreIncludeExcludeWaitingIncludeMethod implements WaitingInclude {

    @Wait(
        includes = {IllegalMonitorStateException.class, IllegalArgumentException.class, Error.class},
        excludes = {IllegalStateException.class, ClassCastException.class, IllegalAccessError.class}
    )
    @Override
    public <T> T includeMethod(Callable<T> callable) throws Exception {
        return callable.call();
    }
}
