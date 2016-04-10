package shiver.me.timbers.waiting.util;

import org.junit.Test;
import shiver.me.timbers.waiting.Options;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static shiver.me.timbers.waiting.random.RandomExceptions.someOtherThrowable;
import static shiver.me.timbers.waiting.random.RandomExceptions.someThrowable;

public class ExcludesTest {

    @Test
    @SuppressWarnings("unchecked")
    public void Can_add_excludes() {

        // Given
        final Options options = mock(Options.class);
        final Throwable throwable1 = someThrowable();
        final Throwable throwable2 = someOtherThrowable();

        // When
        Excludes.addExcludes(options, throwable1, throwable2);

        // Then
        verify(options).excludes(throwable1.getClass(), throwable2.getClass());
        verifyNoMoreInteractions(options);
    }
}