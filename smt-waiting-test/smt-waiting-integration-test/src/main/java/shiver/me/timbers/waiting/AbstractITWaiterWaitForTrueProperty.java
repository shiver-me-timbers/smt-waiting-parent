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

import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingForTrue;
import shiver.me.timbers.waiting.test.WaitingPropertyRuleAware;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public abstract class AbstractITWaiterWaitForTrueProperty extends AbstractITWaiterWaitForTrue
    implements ITWaiterDefaults, WaitingPropertyRuleAware {

    @Override
    public WaitingForTrue waitForTrue(final long duration, final TimeUnit unit, boolean isTrue) {
        return new WaitingForTrue() {
            @Override
            public <T> T waitForTrueMethod(Callable<T> callable) throws Exception {
                properties().addTimeout(duration, unit);
                properties().setProperty("smt.waiting.waitForTrue", "true");
                return defaults().defaultsMethod(callable);
            }
        };
    }

    @SuppressWarnings("SameParameterValue")
    protected abstract WaitingForTrue overrideWaitForTrue(long duration, TimeUnit unit, boolean isTrue);

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void Can_override_the_wait_for_true_system_property() throws Throwable {

        final Callable callable = mock(Callable.class);

        // Given
        properties().setProperty("smt.waiting.waitForTrue", "true");
        given(callable.call()).willReturn(false);

        // When
        final Object actual = overrideWaitForTrue(500L, MILLISECONDS, false).waitForTrueMethod(callable);

        // Then
        assertThat(actual, is((Object) false));
        verify(callable).call();
    }
}
