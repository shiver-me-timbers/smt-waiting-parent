package shiver.me.timbers.waiting;

import org.junit.Test;
import shiver.me.timbers.waiting.test.DefaultUnregisteredService;
import shiver.me.timbers.waiting.test.RegisteredService;
import shiver.me.timbers.waiting.test.TestRegisteredService;
import shiver.me.timbers.waiting.test.UnregisteredService;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SPIServiceClassLoaderTest {

    @Test
    public void Can_load_a_service() {

        // When
        final Class actual = new SPIServiceClassLoader<>(RegisteredService.class).load(null);

        // Then
        assertThat(actual, equalTo((Class) TestRegisteredService.class));
    }

    @Test
    public void Cannot_load_a_service_that_has_not_been_registered() {

        // When
        final Class actual = new SPIServiceClassLoader<UnregisteredService, DefaultUnregisteredService>(
            UnregisteredService.class).load(DefaultUnregisteredService.class);

        // Then
        assertThat(actual, equalTo((Class) DefaultUnregisteredService.class));
    }
}