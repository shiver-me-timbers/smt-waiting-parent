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

/**
 * This is exactly the same as the {@link Waiter} except it can only be configured with {@link SpringOptions}.
 *
 * @author Karl Bennett
 */
public class SpringWaiter extends Waiter {

    public SpringWaiter() {
        this(new SpringOptions());
    }

    public SpringWaiter(SpringOptions options) {
        super(options);
    }
}
