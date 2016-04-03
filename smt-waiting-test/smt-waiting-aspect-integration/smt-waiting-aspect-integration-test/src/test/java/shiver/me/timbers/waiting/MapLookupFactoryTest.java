package shiver.me.timbers.waiting;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomDoubles.someDouble;
import static shiver.me.timbers.data.random.RandomFloats.someFloat;
import static shiver.me.timbers.data.random.RandomIntegers.someInteger;
import static shiver.me.timbers.data.random.RandomLongs.someLong;
import static shiver.me.timbers.data.random.RandomShorts.someShort;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.data.random.RandomThings.someThings;

public class MapLookupFactoryTest {

    private Map<Object, Object> map;
    private MapLookupFactory<Object> factory;

    @Before
    public void setUp() {
        map = mock(Map.class);
        factory = new MapLookupFactory<>(map);
    }

    @Test
    public void Can_lookup_a_class() {

        final List args = someThings(
            (Object) someShort(), someInteger(), someFloat(), someLong(), someDouble(), someString()
        );

        final Object expected = new Object();

        // Given
        given(map.get(args)).willReturn(expected);

        // When
        final Object actual = factory.find(args.toArray(new Object[args.size()]));

        // Then
        assertThat(actual, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void Can_fail_to_lookup_a_class() {

        final List args = someThings(
            (Object) someShort(), someInteger(), someFloat(), someLong(), someDouble(), someString()
        );

        // Given
        given(map.get(args)).willReturn(null);

        // When
        factory.find(args.toArray(new Object[args.size()]));
    }

    @Test
    public void Can_add_a_class() {

        // Given
        final List args = someThings(
            (Object) someShort(), someInteger(), someFloat(), someLong(), someDouble(), someString()
        );
        final Object object = new Object();

        // When
        factory.add(object, args.toArray(new Object[args.size()]));

        // Then
        verify(map).put(args, object);
    }
}