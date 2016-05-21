package shiver.me.timbers.waiting.factory;

import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingDefaultsMethod;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

public class WaitingDefaultsMethodFactoryTest {

    @Test
    public void Can_create_a_waiting_defaults_class_factory() {

        // When
        final WaitingDefaultsMethodFactory actual = new WaitingDefaultsMethodFactory();

        // Then
        assertThat(actual.create(), instanceOf(WaitingDefaultsMethod.class));
    }
}