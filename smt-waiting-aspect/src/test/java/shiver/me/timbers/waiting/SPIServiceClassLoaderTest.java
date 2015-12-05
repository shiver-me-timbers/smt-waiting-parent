/*
 * Copyright 2015 Karl Bennett
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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