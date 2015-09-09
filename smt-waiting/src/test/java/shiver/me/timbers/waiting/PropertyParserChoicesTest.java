package shiver.me.timbers.waiting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.data.random.RandomBooleans.someBoolean;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomLongs.someLong;

public class PropertyParserChoicesTest {

    @Test
    @SuppressWarnings("unchecked")
    public void Can_create_a_system_property_choices() {

        final PropertyParser propertyParser = mock(PropertyParser.class);

        final Choices choices = mock(Choices.class);

        final Long previousTimeoutDuration = someLong();
        final TimeUnit previousTimeoutUnit = someEnum(TimeUnit.class);
        final Long previousIntervalDuration = someLong();
        final TimeUnit previousIntervalUnit = someEnum(TimeUnit.class);
        final Boolean previousWaitForTrue = someBoolean();
        final Boolean previousWaitForNotNull = someBoolean();
        final List<ResultValidator> previousResultValidators = emptyList();
        final long timeoutDuration = someLong();
        final TimeUnit timeoutUnit = someEnum(TimeUnit.class);
        final long intervalDuration = someLong();
        final TimeUnit intervalUnit = someEnum(TimeUnit.class);
        final Boolean waitForTrue = someBoolean();
        final Boolean waitForNotNull = someBoolean();
        final List<ResultValidator> propertyValidators = spy(new ArrayList<ResultValidator>());

        // Given
        given(choices.getTimeoutDuration()).willReturn(previousTimeoutDuration);
        given(choices.getTimeoutUnit()).willReturn(previousTimeoutUnit);
        given(choices.getIntervalDuration()).willReturn(previousIntervalDuration);
        given(choices.getIntervalUnit()).willReturn(previousIntervalUnit);
        given(choices.isWaitForTrue()).willReturn(previousWaitForTrue);
        given(choices.isWaitForNotNull()).willReturn(previousWaitForNotNull);
        given(choices.getResultValidators()).willReturn(previousResultValidators);
        given(propertyParser.getLongProperty("smt.waiting.timeout.duration", previousTimeoutDuration)).willReturn(timeoutDuration);
        given(propertyParser.getEnumProperty("smt.waiting.timeout.unit", previousTimeoutUnit)).willReturn(timeoutUnit);
        given(propertyParser.getLongProperty("smt.waiting.interval.duration", previousIntervalDuration)).willReturn(intervalDuration);
        given(propertyParser.getEnumProperty("smt.waiting.interval.unit", previousIntervalUnit)).willReturn(intervalUnit);
        given(propertyParser.getBooleanProperty("smt.waiting.waitForTrue", previousWaitForTrue)).willReturn(waitForTrue);
        given(propertyParser.getBooleanProperty("smt.waiting.waitForNotNull", previousWaitForNotNull)).willReturn(waitForNotNull);
        given(propertyParser.getInstanceProperty("smt.waiting.waitFor")).willReturn((List) propertyValidators);

        // When
        final Choices actual = new PropertyParserChoices(propertyParser).apply(choices);

        // Then
        verify(propertyValidators).addAll(previousResultValidators);
        assertThat(actual.getTimeoutDuration(), is(timeoutDuration));
        assertThat(actual.getTimeoutUnit(), is(timeoutUnit));
        assertThat(actual.getIntervalDuration(), is(intervalDuration));
        assertThat(actual.getIntervalUnit(), is(intervalUnit));
        assertThat(actual.isWaitForTrue(), is(waitForTrue));
        assertThat(actual.isWaitForNotNull(), is(waitForNotNull));
        assertThat(actual.getResultValidators(), is(propertyValidators));
    }
}