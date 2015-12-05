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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

@RunWith(MockitoJUnitRunner.class)
public class SpringPropertyGetterTest {

    @Mock
    private ApplicationContext context;

    @InjectMocks
    private SpringPropertyGetter getter;

    @Test
    public void Can_get_a_spring_property() {

        final String key = someString();

        final Environment environment = mock(Environment.class);

        final String expected = someString();

        // Given
        given(context.getEnvironment()).willReturn(environment);
        given(environment.getProperty(key)).willReturn(expected);

        // When
        final String actual = getter.get(key);

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void Can_get_a_null_property_when_the_application_context_is_null() {

        // Given
        final String key = someString();

        // When
        final String actual = new SpringPropertyGetter().get(key);

        // Then
        assertThat(actual, nullValue());
    }

    @Test
    public void Can_get_a_spring_property_while_supplying_a_default_value() {

        final String key = someString();

        final Environment environment = mock(Environment.class);
        final Object defaultValue = new Object();

        final String expected = someString();

        // Given
        given(context.getEnvironment()).willReturn(environment);
        given(environment.getProperty(key, defaultValue.toString())).willReturn(expected);

        // When
        final String actual = getter.get(key, defaultValue);

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void Can_get_a_default_spring_property() {

        final String key = someString();

        final Environment environment = mock(Environment.class);

        final Object expected = new Object();

        // Given
        given(context.getEnvironment()).willReturn(environment);
        given(environment.getProperty(key, expected.toString())).willReturn(expected.toString());

        // When
        final String actual = getter.get(key, expected);

        // Then
        assertThat(actual, equalTo(expected.toString()));
    }

    @Test
    public void Can_get_a_default_spring_property_when_the_application_context_is_null() {

        // Given
        final String key = someString();
        final Object expected = new Object();

        // When
        final String actual = new SpringPropertyGetter().get(key, expected);

        // Then
        assertThat(actual, equalTo(expected.toString()));
    }
}