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

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SuppressWarnings("unchecked")
public class WaiterAspectTest {

    private ServiceLoader<WaiterService, OptionsService> waiterLoader;
    private ServiceLoader<OptionsService, Wait> optionsLoader;
    private WaiterAspect aspect;

    @Before
    public void setUp() {
        waiterLoader = mock(ServiceLoader.class);
        optionsLoader = mock(ServiceLoader.class);
        aspect = new WaiterAspect(waiterLoader, optionsLoader);
    }

    @Test
    public void Can_create_a_waiter_aspect() {
        new WaiterAspect();
    }

    @Test
    public void Call_isMethod_for_100_percent_coverage()
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        final Method isMethod = WaiterAspect.class.getDeclaredMethod("isMethod");
        isMethod.setAccessible(true);
        isMethod.invoke(aspect);
    }

    @Test
    public void Can_wait_for_method_in_class() throws Throwable {

        final ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        final Wait wait = mock(Wait.class);

        final WaiterService waiterService = mock(WaiterService.class);
        final OptionsService options = mock(OptionsService.class);

        final String[] toString = new String[1];
        final Object[] success = new Object[1];
        final Object expected = new Object();

        // Given
        given(optionsLoader.load(wait)).willReturn(options);
        given(waiterLoader.load(options)).willReturn(waiterService);
        willAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                final Until until = (Until) invocation.getArguments()[0];
                toString[0] = until.toString();
                return success[0] = until.success();
            }
        }).given(waiterService).wait(any(Until.class));
        given(joinPoint.proceed()).willReturn(expected);

        // When
        final Object actual = aspect.waitOnClass(joinPoint, wait);

        // Then
        assertThat(actual, is(success[0]));
        assertThat(actual, is(expected));
        assertThat(joinPoint.toString(), is(toString[0]));
    }

    @Test
    public void Can_wait_for_method() throws Throwable {

        final ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        final Wait wait = mock(Wait.class);

        final WaiterService waiterService = mock(WaiterService.class);
        final OptionsService options = mock(OptionsService.class);

        final String[] toString = new String[1];
        final Object[] success = new Object[1];
        final Object expected = new Object();

        // Given
        given(optionsLoader.load(wait)).willReturn(options);
        given(waiterLoader.load(options)).willReturn(waiterService);
        willAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                final Until until = (Until) invocation.getArguments()[0];
                toString[0] = until.toString();
                return success[0] = until.success();
            }
        }).given(waiterService).wait(any(Until.class));
        given(joinPoint.proceed()).willReturn(expected);

        // When
        final Object actual = aspect.waitOnMethod(joinPoint, wait);

        // Then
        assertThat(actual, is(success[0]));
        assertThat(actual, is(expected));
        assertThat(joinPoint.toString(), is(toString[0]));
    }

    @Test
    public void A_new_waiter_is_loaded_every_time() throws Throwable {

        final ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        final Wait wait = mock(Wait.class);

        final WaiterService waiterService = mock(WaiterService.class);
        final OptionsService options = mock(OptionsService.class);

        // Given
        given(optionsLoader.load(wait)).willReturn(options);
        given(waiterLoader.load(options)).willReturn(waiterService);

        // When
        aspect.waitOnClass(joinPoint, wait);
        aspect.waitOnMethod(joinPoint, wait);
        aspect.waitOnClass(joinPoint, wait);

        // Then
        verify(optionsLoader, times(3)).load(wait);
        verify(waiterLoader, times(3)).load(options);
        verify(waiterService, times(3)).wait(any(Until.class));
    }
}