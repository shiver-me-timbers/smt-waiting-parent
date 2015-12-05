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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class SingleDependencyInstantiaterTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Can_instantiate_a_class_with_a_single_dependency()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        // Given
        final TestInput input = mock(TestInput.class);
        final Instantiater<TestService, TestInput> instantiater = new SingleDependencyInstantiater<>(
            TestService.class,
            TestInput.class
        );

        // When
        final TestService actual = instantiater
            .instantiate(input);

        // Then
        assertThat(actual.getInput(), is(input));
    }

    @Test
    public void Cannot_instantiate_a_waiter_with_no_input_constructor()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(isA(NoSuchMethodException.class));

        // When
        new SingleDependencyInstantiater<>(TestInput.class, Object.class);
    }

    public static class TestService {
        private final TestInput input;

        public TestService(TestInput input) {
            this.input = input;
        }

        public TestInput getInput() {
            return input;
        }
    }

    public static class TestInput {

    }
}