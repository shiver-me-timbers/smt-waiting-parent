package shiver.me.timbers.waiting;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class SingleInputInstantiaterTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Can_instantiate_a_waiter()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        // Given
        final TestInput input = mock(TestInput.class);

        // When
        final TestService actual = new SingleInputInstantiater<>(TestService.class, TestInput.class).instantiate(input);

        // Then
        assertThat(actual.getInput(), is(input));
    }

    @Test
    public void Can_instantiate_a_waiter_with_no_input_constructor()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(isA(NoSuchMethodException.class));

        // When
        new SingleInputInstantiater<>(TestInput.class, Object.class);
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