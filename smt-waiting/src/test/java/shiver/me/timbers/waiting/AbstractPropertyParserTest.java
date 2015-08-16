package shiver.me.timbers.waiting;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class AbstractPropertyParserTest {

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
    public void Can_get_the_default_enum_property_for_an_invalid_property_value() {

        final String key = someString();
        final AnEnum expected = someEnum(AnEnum.class);

        final String invalidPropertyValue = someAlphaString();

        // Given
        given(propertyGetter.get(key, expected)).willReturn(invalidPropertyValue);

        // When
        final AnEnum actual = parser.getEnumProperty(key, expected);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_get_an_instance_property() {

        final String key = someString();
        final ADefaultClass defaultValue = new ADefaultClass();

        // Given
        given(propertyGetter.get(key)).willReturn(AClass.class.getName());

        // When
        final ADefaultClass actual = parser.getInstanceProperty(key, defaultValue);

        // Then
        assertThat(actual, instanceOf(AClass.class));
    }

    @Test
    public void Will_get_default_instance_for_property_that_does_not_exist() {

        final String key = someString();
        final ADefaultClass defaultValue = new ADefaultClass();

        // Given
        given(propertyGetter.get(key)).willReturn(null);

        // When
        final ADefaultClass actual = parser.getInstanceProperty(key, defaultValue);

        // Then
        assertThat(actual, is(defaultValue));
    }

    @Test
    public void Will_get_default_instance_for_class_that_does_not_exist() {

        final String key = someString();
        final ADefaultClass defaultValue = new ADefaultClass();

        // Given
        given(propertyGetter.get(key)).willReturn("this.class.does.not.Exist");

        // When
        final ADefaultClass actual = parser.getInstanceProperty(key, defaultValue);

        // Then
        assertThat(actual, is(defaultValue));
    }

    @Test
    public void Will_get_default_instance_for_class_that_has_no_default_constructor() {

        final String key = someString();
        final ADefaultClass defaultValue = new ADefaultClass();

        // Given
        given(propertyGetter.get(key)).willReturn(ANonDefaultClass.class.getName());

        // When
        final ADefaultClass actual = parser.getInstanceProperty(key, defaultValue);

        // Then
        assertThat(actual, is(defaultValue));
    }

    @Test
    public void Will_get_default_instance_for_class_with_a_private_constructor() {

        final String key = someString();
        final ADefaultClass defaultValue = new ADefaultClass();

        // Given
        given(propertyGetter.get(key)).willReturn(APrivateClass.class.getName());

        // When
        final ADefaultClass actual = parser.getInstanceProperty(key, defaultValue);

        // Then
        assertThat(actual, is(defaultValue));
    }

    private enum AnEnum {
        A,
        TEST,
        ENUM,
        FOR,
        TESTING
    }

    public static class ADefaultClass {
    }

    public static class AClass extends ADefaultClass {
    }

    public static class ANonDefaultClass extends AClass {
        public ANonDefaultClass(Object argument) {
        }
    }

    private static class APrivateClass extends AClass {
    }
}