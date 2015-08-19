package shiver.me.timbers.waiting;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class SingleDependencyInstantiaterTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Can_instantiate_a_class_with_a_single_dependency()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        // Given
        final TestInput input = mock(TestInput.class);
        final Instantiater<TestService, TestInput> instantiater = new SingleDependencyInstantiater<>(
            TestService.class,
            TestInput.class
        );

        // When
        final TestService actual = instantiater
            .instantiate(input);

        // Then
        assertThat(actual.getInput(), is(input));
    }

    @Test
    public void Cannot_instantiate_a_waiter_with_no_input_constructor()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(isA(NoSuchMethodException.class));

        // When
        new SingleDependencyInstantiater<>(TestInput.class, Object.class);
    }

    public static class TestService {
        private final TestInput input;

        public TestService(TestInput input) {
            this.input = input;
        }

        public TestInput getInput() {
            return input;
        }
    }

    public static class TestInput {

    }
}