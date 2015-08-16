package shiver.me.timbers.waiting;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

public class DefaultConstructorInstantiaterTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Can_instantiate_a_class_with_a_default_constructor()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        // When
        final TestService actual = new DefaultConstructorInstantiater<>(TestService.class).instantiate(null);

        // Then
        assertThat(actual, isA(TestService.class));
    }

    @Test
    public void Cannot_instantiate_a_class_without_a_default_constructor()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(isA(NoSuchMethodException.class));

        // When
        new DefaultConstructorInstantiater<>(NonDefaultTestService.class).instantiate(null);
    }

    public static class TestService {
    }

    public static class NonDefaultTestService {
        public NonDefaultTestService(Object object) {
        }
    }
}