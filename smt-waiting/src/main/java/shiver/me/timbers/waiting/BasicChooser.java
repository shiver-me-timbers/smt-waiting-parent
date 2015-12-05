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

import java.util.List;

/**
 * @author Karl Bennett
 */
class BasicChooser implements Chooser {

    private final Sleeper sleeper;

    public BasicChooser(Sleeper sleeper) {
        this.sleeper = sleeper;
    }

    @Override
    public Choice choose(Choices choices) {
        final List<ResultValidator> resultValidators = choices.getResultValidators();

        if (choices.isWaitForTrue()) {
            resultValidators.add(new TrueResult());
        }
        if (choices.isWaitForNotNull()) {
            resultValidators.add(new NotNullResult());
        }

        return new Choice(
            sleeper,
            choices.getTimeoutDuration(),
            choices.getTimeoutUnit(),
            choices.getIntervalDuration(),
            choices.getIntervalUnit(),
            resultValidators
        );
    }
}
