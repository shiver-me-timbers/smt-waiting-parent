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

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaNumericString;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class SystemPropertyGetterTest {

    private SystemPropertyGetter getter;

    @Before
    public void setUp() {
        getter = new SystemPropertyGetter();
    }

    @Test
    public void Can_get_a_system_property() {

        final String key = someAlphaNumericString(5);

        final String expected = someString(10);

        // Given
        System.setProperty(key, expected);

        // When
        final String actual = getter.get(key);

        // Then
        System.clearProperty(key);
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_get_a_system_property_while_supplying_a_default_value() {

        final String key = someAlphaNumericString();

        final String expected = someString();

        // Given
        System.setProperty(key, expected);

        // When
        final String actual = getter.get(key, new Object());

        // Then
        System.clearProperty(key);
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_get_a_default_system_property() {

        // Given
        final String key = someAlphaNumericString();
        final Object expected = new Object();

        // When
        final String actual = getter.get(key, expected);

        // Then
        System.clearProperty(key);
        assertThat(actual, is(expected.toString()));
    }
}