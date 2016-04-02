package shiver.me.timbers.waiting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.InOrder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class PropertyRuleTest {

    private PropertyManager propertyManager;
    private PropertyRule rule;

    @Before
    public void setUp() {
        propertyManager = mock(PropertyManager.class);
        rule = new PropertyRule(propertyManager);
    }

    @Test
    public void Can_restore_properties_after_a_test_run() throws Throwable {

        // Given
        final Statement statement = mock(Statement.class);

        // When
        rule.apply(statement, mock(Description.class)).evaluate();

        // Then
        final InOrder order = inOrder(statement, propertyManager);
        order.verify(statement).evaluate();
        order.verify(propertyManager).restoreProperties();
    }

    @Test
    public void Can_restore_properties_even_if_the_test_throws_an_exception() throws Throwable {

        final Statement statement = mock(Statement.class);

        final Throwable thrown = mock(Throwable.class);

        // Given
        willThrow(thrown).given(statement).evaluate();

        // When
        try {
            rule.apply(statement, mock(Description.class)).evaluate();
        } catch (Throwable throwable) {
            assertThat(throwable, is(thrown));
        }

        // Then
        final InOrder order = inOrder(statement, propertyManager);
        order.verify(statement).evaluate();
        order.verify(propertyManager).restoreProperties();
    }

    @Test
    public void Can_set_properties() {

        // Given
        final String key = someString();
        final String value = someString();

        // When
        rule.setProperty(key, value);

        // Then
        verify(propertyManager).setProperty(key, value);
    }
}