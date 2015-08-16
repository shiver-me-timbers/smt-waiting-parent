package shiver.me.timbers.waiting;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ReflectionServiceLoaderTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private Instantiater<Object, Object> instantiater;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        instantiater = mock(Instantiater.class);
    }

    @Test
    public void Can_reflectively_create_a_service()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        final Object input = new Object();

        final Object expected = new Object();

        // Given
        given(instantiater.instantiate(input)).willReturn(expected);

        // When
        final Object actual = new ReflectionServiceLoader<>(instantiater).load(input);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Cannot_reflectively_create_a_service_from_an_abstract_class()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(isA(InstantiationException.class));

        final Object input = new Object();

        // Given
        given(instantiater.instantiate(input)).willThrow(new InstantiationException());

        // When
        new ReflectionServiceLoader<>(instantiater).load(input);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Cannot_reflectively_create_a_service_with_the_wrong_target()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(isA(InvocationTargetException.class));

        final Object input = new Object();

        // Given
        given(instantiater.instantiate(input)).willThrow(new InvocationTargetException(new Exception()));

        // When
        new ReflectionServiceLoader<>(instantiater).load(input);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Cannot_reflectively_create_a_service_which_has_a_failing_constructor()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(isA(InstantiationException.class));

        final Object input = new Object();

        // Given
        given(instantiater.instantiate(input)).willThrow(new InstantiationException());

        // When
        new ReflectionServiceLoader<>(instantiater).load(input);
    }
}