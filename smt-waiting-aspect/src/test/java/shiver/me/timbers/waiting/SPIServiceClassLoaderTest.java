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

        // Given
        final ServiceLoader<Class<RegisteredService>, Class<RegisteredService>> loader =
            new SPIServiceClassLoader<>(RegisteredService.class);

        // When
        final Class actual = loader.load(null);

        // Then
        assertThat(actual, equalTo((Class) TestRegisteredService.class));
    }

    @Test
    public void Cannot_load_a_service_that_has_not_been_registered() {

        // Given
        final ServiceLoader<Class<UnregisteredService>, Class<DefaultUnregisteredService>> loader =
            new SPIServiceClassLoader<>(UnregisteredService.class);

        // When
        final Class actual = loader.load(DefaultUnregisteredService.class);

        // Then
        assertThat(actual, equalTo((Class) DefaultUnregisteredService.class));
    }
}