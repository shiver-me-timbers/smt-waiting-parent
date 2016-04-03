package shiver.me.timbers.waiting;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static shiver.me.timbers.waiting.RandomExceptions.someOtherThrowable;
import static shiver.me.timbers.waiting.RandomExceptions.someThrowable;

public class IncludesTest {

    @Test
    public void Can_add_includes() {

        // Given
        final Options options = mock(Options.class);
        final Throwable throwable1 = someThrowable();
        final Throwable throwable2 = someOtherThrowable();

        // When
        Includes.addIncludes(options, throwable1, throwable2);

        // Then
        verify(options).include(throwable1.getClass());
        verify(options).include(throwable2.getClass());
        verifyNoMoreInteractions(options);
    }
}