package shiver.me.timbers.waiting;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLongBetween;

public class WaitOptionsConfigurerTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private OptionsService options;
    private Wait wait;
    private WaitOptionsConfigurer configurer;
    private TimeOut defaultTimeout;
    private Interval defaultInterval;
    private Class<ResultValidator>[] defaultWaitFor;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        options = mock(OptionsService.class);
        wait = mock(Wait.class);
        configurer = new WaitOptionsConfigurer();

        defaultTimeout = mock(TimeOut.class);
        given(defaultTimeout.duration()).willReturn(-1L);
        defaultInterval = mock(Interval.class);
        given(defaultInterval.duration()).willReturn(-1L);
        defaultWaitFor = new Class[0];
    }

    @Test
    public void Can_configure_the_default_values() {

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitFor()).willReturn(defaultWaitFor);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verifyZeroInteractions(options);
    }

    @Test
    public void Can_configure_the_timeout() {

        final TimeOut timeOut = mock(TimeOut.class);
        final Long duration = someLongBetween(0L, 100L);
        final TimeUnit unit = someEnum(TimeUnit.class);

        // Given
        given(wait.value()).willReturn(timeOut);
        given(timeOut.duration()).willReturn(duration);
        given(timeOut.unit()).willReturn(unit);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitFor()).willReturn(defaultWaitFor);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verify(options).withTimeOut(duration, unit);
        verifyNoMoreInteractions(options);
    }

    @Test
    public void Can_configure_the_interval() {

        final Interval interval = mock(Interval.class);

        final Long duration = someLongBetween(0L, 100L);
        final TimeUnit unit = someEnum(TimeUnit.class);

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(interval);
        given(interval.duration()).willReturn(duration);
        given(interval.unit()).willReturn(unit);
        given(wait.waitFor()).willReturn(defaultWaitFor);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verify(options).withInterval(duration, unit);
        verifyNoMoreInteractions(options);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_configure_what_to_wait_for() {

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitFor()).willReturn(new Class[]{TestResultOne.class, TestResultTwo.class});

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        assertThat(actual, is(options));
        verify(options, times(2)).waitFor(any(TestResultOne.class));
        verify(options, times(2)).waitFor(any(TestResultTwo.class));
        verifyNoMoreInteractions(options);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_configure_what_to_wait_for_result_when_a_validator_does_not_have_a_default_constructor() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(isA(InstantiationException.class));

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitFor()).willReturn(new Class[]{TestResultOne.class, NoDefaultTestResult.class});

        // When
        configurer.apply(options, wait);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Can_configure_what_to_wait_for_result_when_a_validator_does_not_have_a_public_constructor() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectCause(isA(IllegalAccessException.class));

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitFor()).willReturn(new Class[]{TestResultOne.class, NoPublicTestResult.class});

        // When
        configurer.apply(options, wait);
    }

    @Test
    public void Can_configure_what_to_wait_for_true() {

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitFor()).willReturn(defaultWaitFor);
        given(wait.waitForTrue()).willReturn(true);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verify(options).willWaitForTrue();
        verifyNoMoreInteractions(options);
    }

    @Test
    public void Can_configure_what_to_wait_for_not_null() {

        // Given
        given(wait.value()).willReturn(defaultTimeout);
        given(wait.interval()).willReturn(defaultInterval);
        given(wait.waitFor()).willReturn(defaultWaitFor);
        given(wait.waitForNotNull()).willReturn(true);

        // When
        final OptionsService actual = configurer.apply(options, wait);

        // Then
        assertThat(actual, is(options));
        verify(options).willWaitForNotNull();
        verifyNoMoreInteractions(options);
    }

    public static class TestResultOne implements ResultValidator {
        @Override
        public boolean isValid(Object result) throws Throwable {
            throw new UnsupportedOperationException();
        }
    }

    public static class TestResultTwo implements ResultValidator {
        @Override
        public boolean isValid(Object result) throws Throwable {
            throw new UnsupportedOperationException();
        }
    }

    public static class NoDefaultTestResult implements ResultValidator {

        public NoDefaultTestResult(Object object) {
        }

        @Override
        public boolean isValid(Object result) throws Throwable {
            throw new UnsupportedOperationException();
        }
    }

    public static class NoPublicTestResult implements ResultValidator {

        public NoPublicTestResult() throws IllegalAccessException {
            throw new IllegalAccessException();
        }

        @Override
        public boolean isValid(Object result) throws Throwable {
            throw new UnsupportedOperationException();
        }
    }
}