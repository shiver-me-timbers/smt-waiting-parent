package shiver.me.timbers.waiting.util;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.data.random.RandomDoubles.someDouble;
import static shiver.me.timbers.data.random.RandomFloats.someFloat;
import static shiver.me.timbers.data.random.RandomIntegers.someInteger;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.data.random.RandomShorts.someShort;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.waiting.util.Classes.toClasses;

public class ClassesTest {

    @Test
    public void Can_convert_objects_to_classes() {

        // Given
        final List objects = asList(
            (Object) someShort(), someInteger(), someFloat(), someLong(), someDouble(), someString()
        );

        // When
        final List<Class> actual = toClasses(objects);

        // Then
        assertThat(actual, hasSize(objects.size()));
        for (Object object : objects) {
            assertThat(actual, hasItem(object.getClass()));
        }
    }
}