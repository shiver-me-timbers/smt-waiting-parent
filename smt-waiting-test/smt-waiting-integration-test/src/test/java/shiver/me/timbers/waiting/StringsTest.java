package shiver.me.timbers.waiting;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.data.random.RandomDoubles.someDouble;
import static shiver.me.timbers.data.random.RandomFloats.someFloat;
import static shiver.me.timbers.data.random.RandomIntegers.someInteger;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.data.random.RandomShorts.someShort;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.data.random.RandomThings.someThings;

public class StringsTest {

    @Test
    public void Can_get_class_names() {

        // Given
        final List<Object> objects = someThings(
            new Object(), someShort(), someInteger(), someLong(), someFloat(), someDouble(), someString()
        );

        // When
        final List<String> actual = Strings.classNames(objects);

        // Then
        assertThat(actual, equalTo(classNames(objects)));
    }

    @Test
    public void Can_get_no_class_names_from_a_null_list() {

        // When
        final List<String> actual = Strings.classNames(null);

        // Then
        assertThat(actual, Matchers.<String>empty());
    }

    @Test
    public void Can_get_no_class_names_from_an_empty_list() {

        // When
        final List<String> actual = Strings.classNames(emptyList());

        // Then
        assertThat(actual, Matchers.<String>empty());
    }

    @Test
    public void Can_concatenate_class_string_with_a_delimiter() {

        // Given
        final String delimiter = someString(1);
        final List<Object> objects = someThings(
            new Object(), someShort(), someInteger(), someLong(), someFloat(), someDouble(), someString()
        );

        // When
        final String actual = Strings.concat(delimiter, objects.toArray(new Object[objects.size()]));

        // Then
        assertThat(actual, equalTo(concat(objects, delimiter)));
    }

    @Test
    public void Can_create_an_empty_string_from_a_null_array() {

        // When
        final String actual = Strings.concat(someString(1), (Object[]) null);

        // Then
        assertThat(actual, isEmptyString());
    }

    @Test
    public void Can_create_an_empty_string_from_an_empty_array() {

        // When
        final String actual = Strings.concat(someString(1));

        // Then
        assertThat(actual, isEmptyString());
    }

    @Test
    public void Can_create_an_empty_string_from_a_null_list() {

        // When
        final String actual = Strings.concat(null, someString(1));

        // Then
        assertThat(actual, isEmptyString());
    }

    @Test
    public void Can_create_an_empty_string_from_an_empty_list() {

        // When
        final String actual = Strings.concat(emptyList(), someString(1));

        // Then
        assertThat(actual, isEmptyString());
    }

    private List<String> classNames(List<Object> objects) {
        final List<String> names = new ArrayList<>();
        for (Object object : objects) {
            names.add(object.getClass().getName());
        }
        return names;
    }

    private String concat(List objects, String delimiter) {
        if (objects.isEmpty()) {
            return "";
        }

        final StringBuilder builder = new StringBuilder(objects.remove(0).toString());

        for (Object object : objects) {
            builder.append(delimiter).append(object.toString());
        }

        return builder.toString();
    }
}