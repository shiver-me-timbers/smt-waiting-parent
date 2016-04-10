package shiver.me.timbers.waiting.util;

import org.junit.Test;
import shiver.me.timbers.waiting.Options;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static shiver.me.timbers.waiting.random.RandomExceptions.someOtherThrowable;
import static shiver.me.timbers.waiting.random.RandomExceptions.someThrowable;

public class IncludesTest {

    @Test
    @SuppressWarnings("unchecked")
    public void Can_add_includes() {

        // Given
        final Options options = mock(Options.class);
        final Throwable throwable1 = someThrowable();
        final Throwable throwable2 = someOtherThrowable();

        // When
        Includes.addIncludes(options, throwable1, throwable2);

        // Then
        verify(options).includes(throwable1.getClass(), throwable2.getClass());
        verifyNoMoreInteractions(options);
    }
}