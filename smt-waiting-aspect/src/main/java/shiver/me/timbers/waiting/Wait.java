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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static shiver.me.timbers.waiting.Decision.UNDECIDED;

/**
 * Add this annotation to any class or method to wrap the method call within a {@link Waiter}. Annotating at the class
 * level will wrap all methods within that class.
 * <p>
 * All {@link Options} values can be set with this annotation.
 * <p>
 * Note: This annotation is not inherited.
 *
 * @author Karl Bennett
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Wait {

    /**
     * Set how long we should wait for the method to succeed.
     */
    Timeout value() default @Timeout(duration = -1, unit = SECONDS);

    /**
     * Set how long we should wait in between executions of the method.
     */
    Interval interval() default @Interval(duration = -1, unit = MILLISECONDS);

    /**
     * Add {@code ResultValidator}s that must pass before the method is considered to have succeeded in executing.
     */
    Class<? extends ResultValidator>[] waitFor() default {};

    /**
     * If set to true any {@code ResultValidator}s set through global properties will be ignored.
     */
    boolean clearWaitFor() default false;

    /**
     * If set to {@link Decision#YES} the methods execution will not be considered successful until it returns true.
     */
    Decision waitForTrue() default UNDECIDED;

    /**
     * If set to {@link Decision#YES} the methods execution will not be considered successful until it returns a nonnull
     * value.
     */
    Decision waitForNotNull() default UNDECIDED;

    /**
     * The {@code Waiter} will rerun the method being waited on if it throws any {@code Throwable}s added to the include
     * list, any other {@code Throwable}s will be instantly rethrown and the method will not be rerun.
     */
    Class<? extends Throwable>[] includes() default {};

    /**
     * The {@code Waiter} will not rerun the method being waited on if it throws any {@code Throwable}s added to the
     * exclude list, it will instead rethrow. Any other {@code Throwable}s will cause the method to be rerun.
     */
    Class<? extends Throwable>[] excludes() default {};

    /**
     * If set to true {@code Waiter} will ignore all global {@code Options} set through properties and just use the
     * normal default values.
     */
    boolean withDefaults() default false;
}
