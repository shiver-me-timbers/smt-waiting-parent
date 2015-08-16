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