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
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringWaiterConfiguration.class, ITSpringWaiterWaitForTrue.class})
@Configuration
@PropertySource("classpath:wait-for-true.properties")
@DirtiesContext(classMode = AFTER_CLASS)
public class ITSpringWaiterWaitForTrue {

    @Test
    public void Can_set_wait_for_true_with_spring_properties() throws Throwable {

        final Until until = mock(Until.class);

        // Given
        given(until.success()).willReturn(false, false, true);

        // When
        final Object actual = new SpringWaiter().wait(until);

        // Then
        assertThat(actual, equalTo((Object) true));
        verify(until, times(3)).success();
    }
}
