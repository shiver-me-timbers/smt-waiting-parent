package shiver.me.timbers.waiting;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyReflectionEquals;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class ChoiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Can_start_a_timeout() {

        // Given
        final Long duration = someLong();
        final TimeUnit unit = someEnum(TimeUnit.class);

        // When
        final Timer actual = new Choice(
            mock(Sleeper.class),
            duration,
            unit,
            someLong(), someEnum(TimeUnit.class), Collections.<ResultValidator>emptyList()
        ).startTimer();

        // Then
        assertPropertyReflectionEquals("duration", duration, actual);
        assertPropertyReflectionEquals("unit", unit, actual);
    }

    @Test
    public void Can_validate_result() throws Throwable {

        final Object result = new Object();

        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator = mock(ResultValidator.class);

        final boolean expected = someBoolean();

        // Given
        given(resultValidator.isValid(result)).willReturn(expected);

        // When
        final boolean actual = new Choice(
            mock(Sleeper.class),
            someLong(),
            someEnum(TimeUnit.class),
            someLong(), someEnum(TimeUnit.class), Collections.<ResultValidator>singletonList(resultValidator)
        ).isValid(result);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_have_no_result_validation() throws Throwable {

        // When
        final boolean actual = new Choice(
            mock(Sleeper.class),
            someLong(),
            someEnum(TimeUnit.class),
            someLong(), someEnum(TimeUnit.class), Collections.<ResultValidator>emptyList()
        ).isValid(null);

        // Then
        assertThat(actual, is(true));
    }

    @Test
    public void Can_validate_result_in_multiple_ways() throws Throwable {

        final Object result = new Object();
        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator1 = mock(ResultValidator.class);
        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator2 = mock(ResultValidator.class);
        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator3 = mock(ResultValidator.class);

        final boolean expected = someBoolean();

        // Given
        given(resultValidator1.isValid(result)).willReturn(true);
        given(resultValidator2.isValid(result)).willReturn(true);
        given(resultValidator3.isValid(result)).willReturn(expected);

        // When
        final boolean actual = new Choice(
            mock(Sleeper.class),
            someLong(),
            someEnum(TimeUnit.class),
            someLong(), someEnum(TimeUnit.class), Arrays.<ResultValidator>asList(resultValidator1, resultValidator2, resultValidator3)
        ).isValid(result);

        // Then
        assertThat(actual, is(expected));
        verify(resultValidator1).isValid(result);
        verify(resultValidator2).isValid(result);
        verify(resultValidator3).isValid(result);
    }

    @Test
    public void Can_stop_on_first_result_validation_failure() throws Throwable {

        final Object result = new Object();
        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator1 = mock(ResultValidator.class);
        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator2 = mock(ResultValidator.class);
        @SuppressWarnings("unchecked")
        final ResultValidator<Object> resultValidator3 = mock(ResultValidator.class);

        // Given
        given(resultValidator1.isValid(result)).willReturn(true);
        given(resultValidator2.isValid(result)).willReturn(false);

        // When
        final boolean actual = new Choice(
            mock(Sleeper.class),
            someLong(),
            someEnum(TimeUnit.class),
            someLong(), someEnum(TimeUnit.class), Arrays.<ResultValidator>asList(resultValidator1, resultValidator2, resultValidator3)
        ).isValid(result);

        // Then
        assertThat(actual, is(false));
        verify(resultValidator1).isValid(result);
        verify(resultValidator2).isValid(result);
        verifyZeroInteractions(resultValidator3);
    }

    @Test
    public void Can_pause_for_an_interval() throws InterruptedException {

        // Given
        final Sleeper sleeper = mock(Sleeper.class);
        final long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);

        // When
        new Choice(
            sleeper,
            someLong(),
            someEnum(TimeUnit.class),
            intervalDuration, intervalUnit, Collections.<ResultValidator>emptyList()
        ).interval();

        // Then
        verify(sleeper).sleep(intervalUnit.toMillis(intervalDuration));
    }

    @Test
    public void Can_have_an_interval_interrupted() throws InterruptedException {

        expectedException.expect(RuntimeException.class);
        expectedException.expectCause(isA(InterruptedException.class));

        final Sleeper sleeper = mock(Sleeper.class);
        final long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);

        // Given
        willThrow(new InterruptedException()).given(sleeper).sleep(intervalUnit.toMillis(intervalDuration));

        // When
        new Choice(
            sleeper,
            someLong(),
            someEnum(TimeUnit.class),
            intervalDuration, intervalUnit, Collections.<ResultValidator>emptyList()
        ).interval();
    }
}