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

import java.util.List;

import static shiver.me.timbers.data.random.RandomThings.someThing;
import static shiver.me.timbers.data.random.RandomThings.someThings;

public class RandomExceptions {

    public static Throwable[] SOME_THROWABLES = {new RuntimeException(), new IllegalArgumentException(), new Error()};

    public static Throwable[] SOME_OTHER_THROWABLES = {
        new IllegalStateException(), new ClassCastException(), new IllegalAccessError()
    };

    public static Throwable someThrowable() {
        return someThing(SOME_THROWABLES);
    }

    public static List<Throwable> someThrowables() {
        return someThings(SOME_THROWABLES);
    }

    public static Throwable someOtherThrowable() {
        return someThing(SOME_OTHER_THROWABLES);
    }

    public static List<Throwable> someOtherThrowables() {
        return someThings(SOME_OTHER_THROWABLES);
    }
}
