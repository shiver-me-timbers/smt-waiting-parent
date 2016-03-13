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
 * Waits {@link Until} an execution succeeds by not throwing an exception and fulfilling any requirements set by the
 * {@link Options} passed into the constructor or {@code Options} timeout is reached.
 *
 * @author Karl Bennett
 */
public class Waiter implements WaiterService {

    final Choice choice;

    /**
     * Create a new waiter with the default options.
     * <code>
     * smt.waiting.timeout.duration  # 10
     * smt.waiting.timeout.unit      # SECONDS
     * smt.waiting.interval.duration # 100
     * smt.waiting.interval.unit     # MILLISECONDS
     * smt.waiting.waitForTrue       # false
     * smt.waiting.waitForNotNull    # false
     * smt.waiting.waitFor           # empty
     * smt.waiting.include           # empty
     * smt.waiting.exclude           # empty
     * </code>
     */
    public Waiter() {
        this(new Options());
    }

    /**
     * Create a new waiter with custom options.
     */
    public Waiter(Options options) {
        this.choice = options.choose();
    }

    /**
     * Wait for the {@link Until} execution to succeed and meet the requirements set by the waiters {@link Options}.
     *
     * @param until the operation that will be executed until success or timeout.
     * @param <T>   the type returned by the method that is being waited on.
     * @return the result of the waited method call or {@code null} for a {@code void} method.
     */
    public <T> T wait(Until<T> until) {

        final Timer timer = choice.startTimer();

        T result = null;
        Thrower thrower = new Thrower(until, choice);

        while (!timer.exceeded()) {
            try {
                result = until.success();
                thrower.clear();
                if (choice.isValid(result)) {
                    return result;
                }
            } catch (Throwable e) {
                thrower.register(e);
                thrower.throwIfNotSuppressed();
            }
            choice.interval();
        }

        thrower.throwIfRegistered();

        return result;
    }

    private static class Thrower {

        private final Until until;
        private final Choice choice;
        private Throwable throwable;

        public Thrower(Until until, Choice choice) {
            this.until = until;
            this.choice = choice;
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

        public void throwIfNotSuppressed() {
            if (!choice.isSuppressed(throwable)) {
                throwIfRegistered();
            }
        }
    }
}
