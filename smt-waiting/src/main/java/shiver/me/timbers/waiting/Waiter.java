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
 * Waits until some evaluation succeeds or the wait times out.
 *
 * @author Karl Bennett
 */
public class Waiter implements WaiterService {

    private Options options;

    public Waiter() {
        this(new Options());
    }

    public Waiter(Options options) {
        this.options = options;
    }

    public <T> T wait(Until<T> until) {

        final Choice choice = options.choose();

        final Timer timer = choice.startTimer();

        T result = null;
        Thrower thrower = new Thrower(until);

        while (!timer.exceeded()) {
            try {
                result = until.success();
                thrower.clear();
                if (choice.isValid(result)) {
                    return result;
                }
            } catch (Throwable e) {
                thrower.register(e);
            }
            choice.interval();
        }

        thrower.throwIfRegistered();

        return result;
    }

    private static class Thrower {

        private final Until until;
        private Throwable throwable;

        public Thrower(Until until) {
            this.until = until;
        }

        public void clear() {
            throwable = null;
        }

        public void register(Throwable throwable) {
            this.throwable = throwable;
        }

        public void throwIfRegistered() {

            if (isRuntime()) {
                throw (RuntimeException) throwable;
            }

            if (isError()) {
                throw (Error) throwable;
            }

            if (isChecked()) {
                throw new WaitedTooLongException(until, throwable);
            }
        }

        private boolean isRuntime() {
            return throwable != null && throwable instanceof RuntimeException;
        }

        private boolean isError() {
            return throwable != null && throwable instanceof Error;
        }

        private boolean isChecked() {
            return throwable != null;
        }
    }
}
