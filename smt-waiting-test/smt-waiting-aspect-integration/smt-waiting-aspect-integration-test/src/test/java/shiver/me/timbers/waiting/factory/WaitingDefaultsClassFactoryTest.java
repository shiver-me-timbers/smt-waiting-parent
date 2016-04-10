package shiver.me.timbers.waiting.factory;

import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingDefaultsClass;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

public class WaitingDefaultsClassFactoryTest {


    @Test
    public void Can_create_a_waiting_defaults_class_factory() {

        // When
        final WaitingDefaultsClassFactory actual = new WaitingDefaultsClassFactory();

        // Then
        assertThat(actual.create(), instanceOf(WaitingDefaultsClass.class));
    }
}