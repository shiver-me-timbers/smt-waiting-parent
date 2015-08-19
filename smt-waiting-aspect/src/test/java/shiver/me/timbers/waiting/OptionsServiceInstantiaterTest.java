package shiver.me.timbers.waiting;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
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