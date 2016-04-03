package shiver.me.timbers.waiting;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class DynamicPropertySourceTest {

    private Map<String, String> map;
    private DynamicPropertySource propertySource;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        map = mock(Map.class);
        propertySource = new DynamicPropertySource(map);
    }

    @Test
    public void Can_get_a_property() {

        final String key = someString();

        final String expected = someString();

        // Given
        given(map.get(key)).willReturn(expected);

        // When
        final String actual = propertySource.getProperty(key);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_set_a_property() {

        // Given
        final String key = someString();
        final String value = someString();

        // When
        propertySource.setProperty(key, value);

        // Then
        verify(map).put(key, value);
    }

    @Test
    public void Can_restore_all_properties() {

        // When
        propertySource.restoreProperties();

        // Then
        verify(map).clear();
    }
}