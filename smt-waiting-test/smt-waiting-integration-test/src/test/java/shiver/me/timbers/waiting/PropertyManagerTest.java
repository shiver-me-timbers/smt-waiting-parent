package shiver.me.timbers.waiting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaNumericString;

public class PropertyManagerTest {

    private PropertyManager propertyManager;

    @Before
    public void setUp() {
        propertyManager = new PropertyManager();
    }

    @After
    public void tearDown() {
        propertyManager.restoreProperties();
    }

    @Test
    public void Can_set_a_property() {

        // Given
        String key = someAlphaNumericString(5);
        String value = someAlphaNumericString(8);

        // When
        propertyManager.setProperty(key, value);

        // Then
        assertThat(System.getProperty(key), is(value));
    }

    @Test
    public void Can_restore_a_new_property() {

        // Given
        String key = someAlphaNumericString(5);
        String value = someAlphaNumericString(8);

        // When
        propertyManager.setProperty(key, value);
        propertyManager.restoreProperties();

        // Then
        assertThat(System.getProperties().containsKey(key), is(false));
    }

    @Test
    public void Can_restore_an_existing_property() {

        // Given
        String key = someAlphaNumericString(5);
        String value = someAlphaNumericString(8);
        System.setProperty(key, value);

        // When
        propertyManager.setProperty(key, someAlphaNumericString(13));
        propertyManager.restoreProperties();

        // Then
        assertThat(System.getProperties().containsKey(key), is(true));
        assertThat(System.getProperty(key), is(value));
        System.clearProperty(key);
    }
}