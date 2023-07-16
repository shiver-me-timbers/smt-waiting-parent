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

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OptionsServiceInstantiaterTest {

    @Test
    public void Can_instantiate_an_options()
        throws IllegalAccessException, InvocationTargetException, InstantiationException {

        @SuppressWarnings("unchecked")
        final Instantiater<OptionsService, Void> instantiater = mock(Instantiater.class);
        @SuppressWarnings("unchecked")
        final OptionsConfigurer<Wait> optionsConfigurer = mock(OptionsConfigurer.class);
        final Instantiater<OptionsService, Wait> serviceInstantiater = new OptionsServiceInstantiater(
            instantiater,
            optionsConfigurer
        );

        final Wait wait = mock(Wait.class);

        final OptionsService options = mock(OptionsService.class);

        final OptionsService expected = mock(OptionsService.class);

        // Given
        given(instantiater.instantiate(null)).willReturn(options);
        given(optionsConfigurer.apply(options, wait)).willReturn(expected);

        // When
        final OptionsService actual = serviceInstantiater.instantiate(wait);

        // Then
        assertThat(actual, is(expected));
    }
}