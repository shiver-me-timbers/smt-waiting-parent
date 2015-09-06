package shiver.me.timbers.waiting;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static java.lang.String.format;
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
    public void Can_handle_an_invalid_property_value() {

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
    public void Can_get_a_single_instance_property() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(AClass.class.getName());

        // When
        final List<AClass> actual = parser.getInstanceProperty(key);

        // Then
        assertThat(actual, contains(instanceOf(AClass.class)));
    }

    @Test
    public void Can_get_a_multiple_instance_property() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(format("%s,%s", AClass.class.getName(), AnotherClass.class.getName()));

        // When
        final List<AClass> actual = parser.getInstanceProperty(key);

        // Then
        assertThat(actual, contains(instanceOf(AClass.class), instanceOf(AnotherClass.class)));
    }

    @Test
    public void Will_get_an_empty_list_for_a_property_that_does_not_exist() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(null);

        // When
        final List<AClass> actual = parser.getInstanceProperty(key);

        // Then
        assertThat(actual, empty());
    }

    @Test(expected = IllegalStateException.class)
    public void Will_get_an_exception_for_a_class_that_does_not_exist() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn("this.class.does.not.Exist");

        // When
        parser.getInstanceProperty(key);
    }

    @Test(expected = IllegalStateException.class)
    public void Will_an_exception_for_a_class_that_has_no_default_constructor() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(ANonDefaultClass.class.getName());

        // When
        parser.getInstanceProperty(key);
    }

    @Test(expected = IllegalStateException.class)
    public void Will_get_an_exception_for_a_class_with_a_private_constructor() {

        final String key = someString();

        // Given
        given(propertyGetter.get(key)).willReturn(APrivateClass.class.getName());

        // When
        parser.getInstanceProperty(key);
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