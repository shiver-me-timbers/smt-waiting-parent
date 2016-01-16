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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class AbstractPropertyParserTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private PropertyGetter propertyGetter;
    private AbstractPropertyParser parser;

    @Before
    public void setUp() {
        propertyGetter = mock(PropertyGetter.class);
        parser = new AbstractPropertyParser(propertyGetter) {
        };
    }

    @Test
    public void Can_get_a_long_property() {

        final String key = someString();
        final Long defaultValue = someLong();

        final Long expected = someLong();

        // Given
        given(propertyGetter.get(key, defaultValue)).willReturn(expected.toString());

        // When
        final Long actual = parser.getLongProperty(key, defaultValue);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_get_a_boolean_property() {

        final String key = someString();
        final Boolean defaultValue = someBoolean();

        final Boolean expected = someBoolean();

        // Given
        given(propertyGetter.get(key, defaultValue)).willReturn(expected.toString());

        // When
        final Boolean actual = parser.getBooleanProperty(key, defaultValue);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_get_an_enum_property() {

        final String key = someString();
        final AnEnum defaultValue = someEnum(AnEnum.class);

        final AnEnum expected = someEnum(AnEnum.class);

        // Given
        given(propertyGetter.get(key, defaultValue)).willReturn(expected.toString());

        // When
        final AnEnum actual = parser.getEnumProperty(key, defaultValue);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_handle_an_invalid_enum_property_value() {

        final String invalidPropertyValue = someString();

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(containsString(invalidPropertyValue));

        final String key = someString();
        final AnEnum expected = someEnum(AnEnum.class);

        // Given
        given(propertyGetter.get(key, expected)).willReturn(invalidPropertyValue);

        // When
        parser.getEnumProperty(key, expected);
    }

    @Test
    public void Can_get_a_single_class_property() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(AClass.class.getName());

        // When
        final List<Class<AClass>> actual = parser.getClassProperty(key, Collections.<Class<AClass>>emptyList());

        // Then
        assertThat(actual, contains(AClass.class));
    }

    @Test
    public void Can_get_a_multiple_class_property() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(format("%s,%s", AClass.class.getName(), AnotherClass.class.getName()));

        // When
        final List<Class<AClass>> actual = parser.getClassProperty(key, Collections.<Class<AClass>>emptyList());

        // Then
        assertThat(actual, contains(AClass.class, AnotherClass.class));
    }

    @Test
    public void Can_merge_a_class_property_with_a_manually_set_class() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(AClass.class.getName());

        // When
        @SuppressWarnings("unchecked")
        final List<Class<? extends AClass>> actual = parser.getClassProperty(key, (List) asList(AnotherClass.class));

        // Then
        assertThat(actual, contains((Class) AnotherClass.class, (Class) AClass.class));
    }

    @Test
    public void Will_get_an_empty_list_for_a_class_property_that_does_not_exist() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(null);

        // When
        final List<Class<AClass>> actual = parser.getClassProperty(key, Collections.<Class<AClass>>emptyList());

        // Then
        assertThat(actual, empty());
    }

    @Test(expected = IllegalStateException.class)
    public void Will_get_an_exception_for_a_class_that_does_not_exist() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn("this.class.does.not.Exist");

        // When
        parser.getClassProperty(key, Collections.<Class<AClass>>emptyList());
    }

    @Test
    public void Can_get_a_single_instance_property() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(AClass.class.getName());

        // When
        final List<AClass> actual = parser.getInstanceProperty(key, Collections.<AClass>emptyList());

        // Then
        assertThat(actual, contains(instanceOf(AClass.class)));
    }

    @Test
    public void Can_get_a_multiple_instance_property() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(format("%s,%s", AClass.class.getName(), AnotherClass.class.getName()));

        // When
        final List<AClass> actual = parser.getInstanceProperty(key, Collections.<AClass>emptyList());

        // Then
        assertThat(actual, contains(instanceOf(AClass.class), instanceOf(AnotherClass.class)));
    }

    @Test
    public void Can_merge_an_instance_property_with_a_manually_set_property() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(AClass.class.getName());

        // When
        @SuppressWarnings("unchecked")
        final List<AClass> actual = parser.getInstanceProperty(key, asList((AClass) new AnotherClass()));

        // Then
        assertThat(actual, contains(instanceOf(AnotherClass.class), instanceOf(AClass.class)));
    }

    @Test
    public void Will_get_an_empty_list_for_an_instance_property_that_does_not_exist() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(null);

        // When
        final List<AClass> actual = parser.getInstanceProperty(key, Collections.<AClass>emptyList());

        // Then
        assertThat(actual, empty());
    }

    @Test(expected = IllegalStateException.class)
    public void Will_get_an_exception_for_an_instance_property_class_that_does_not_exist() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn("this.class.does.not.Exist");

        // When
        parser.getInstanceProperty(key, Collections.<AClass>emptyList());
    }

    @Test(expected = IllegalStateException.class)
    public void Will_an_exception_for_an_instance_property_class_that_has_no_default_constructor() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(ANonDefaultClass.class.getName());

        // When
        parser.getInstanceProperty(key, Collections.<AClass>emptyList());
    }

    @Test(expected = IllegalStateException.class)
    public void Will_get_an_exception_for_an_instance_property_class_with_a_private_constructor() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(APrivateClass.class.getName());

        // When
        parser.getInstanceProperty(key, Collections.<AClass>emptyList());
    }

    private enum AnEnum {
        A,
        TEST,
        ENUM,
        FOR,
        TESTING
    }

    public static class AClass {
    }

    public static class AnotherClass extends AClass {
    }

    public static class ANonDefaultClass extends AClass {
        public ANonDefaultClass(Object argument) {
        }
    }

    private static class APrivateClass extends AClass {
    }
}