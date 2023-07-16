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

import static org.hamcrest.Matchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefaultConstructorInstantiaterTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Can_instantiate_a_class_with_a_default_constructor()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        // Given
        final Instantiater<TestService, Void> instantiater = new DefaultConstructorInstantiater<>(TestService.class);

        // When
        final TestService actual = instantiater.instantiate(null);

        // Then
        assertThat(actual, isA(TestService.class));
    }

    @Test
    public void Cannot_instantiate_a_class_without_a_default_constructor()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(isA(NoSuchMethodException.class));

        // When
        new DefaultConstructorInstantiater<>(NonDefaultTestService.class).instantiate(null);
    }

    public static class TestService {
    }

    public static class NonDefaultTestService {
        @SuppressWarnings("unused")
        public NonDefaultTestService(Object object) {
        }
    }
}