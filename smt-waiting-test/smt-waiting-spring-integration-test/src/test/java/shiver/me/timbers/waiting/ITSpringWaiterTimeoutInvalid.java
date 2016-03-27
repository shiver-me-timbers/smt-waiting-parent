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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringWaiterConfiguration.class, ITSpringWaiterTimeoutInvalid.class})
@Configuration
@PropertySource("classpath:duration-invalid.properties")
@DirtiesContext(classMode = AFTER_CLASS)
public class ITSpringWaiterTimeoutInvalid {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Can_handle_invalid_time_unit_property() throws Throwable {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(containsString("INVALID"));

        final Until until = mock(Until.class);

        // Given
        given(until.success()).willThrow(new Exception());

        // When
        new SpringWaiter().wait(until);
    }
}
