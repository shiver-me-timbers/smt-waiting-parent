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

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

/**
 * @author Karl Bennett
 */
class Timer {

    private final Long duration;
    private final TimeUnit unit;
    private final Start start;

    public Timer(Long duration, TimeUnit unit, Start start) {
        this.duration = duration;
        this.unit = unit;
        this.start = start;
    }

    public boolean exceeded() {
        return start.add(duration, unit).before(new Date());
    }
}
