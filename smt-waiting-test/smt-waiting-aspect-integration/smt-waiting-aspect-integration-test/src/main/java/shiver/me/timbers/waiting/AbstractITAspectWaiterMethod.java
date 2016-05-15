package shiver.me.timbers.waiting;

import org.junit.Test;
import shiver.me.timbers.waiting.execution.WaitingClassLevelOverride;
import shiver.me.timbers.waiting.factory.OverrideClassLevelWaitingMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingExcludeMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingExcludesWithIncludesMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingForMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingForNotNullMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingForTrueMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingIncludeMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingIncludesWithExcludesMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingIntervalMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingTimeoutMethodFactory;

import java.util.concurrent.Callable;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public abstract class AbstractITAspectWaiterMethod extends AbstractITAspectWaiter {

    @Override
    public WaitingIntervalMethodFactory intervalFactory() {
        return new WaitingIntervalMethodFactory();
    }

    @Override
    public WaitingTimeoutMethodFactory timeoutFactory() {
        return new WaitingTimeoutMethodFactory();
    }

    @Override
    public WaitingForMethodFactory waitForFactory() {
        return new WaitingForMethodFactory();
    }

    @Override
    public WaitingForNotNullMethodFactory waitForNotNullFactory() {
        return new WaitingForNotNullMethodFactory();
    }

    @Override
    public WaitingForTrueMethodFactory waitForTrueFactory() {
        return new WaitingForTrueMethodFactory();
    }

    @Override
    public WaitingIncludeMethodFactory includesFactory() {
        return new WaitingIncludeMethodFactory();
    }

    @Override
    public WaitingIncludesWithExcludesMethodFactory includesWithExcludesFactory() {
        return new WaitingIncludesWithExcludesMethodFactory();
    }

    @Override
    public WaitingExcludeMethodFactory excludesFactory() {
        return new WaitingExcludeMethodFactory();
    }

    @Override
    public WaitingExcludesWithIncludesMethodFactory excludesWithIncludesFactory() {
        return new WaitingExcludesWithIncludesMethodFactory();
    }

    public OverrideClassLevelWaitingMethodFactory overrideClassLevelWaitingMethodFactory() {
        return new OverrideClassLevelWaitingMethodFactory();
    }

    protected WaitingClassLevelOverride methodOverrideAspectWaiter() {
        return overrideClassLevelWaitingMethodFactory().create();
    }

    @Test
    public void Can_override_a_class_level_wait_with_a_method_level_wait() throws Exception {

        @SuppressWarnings("unchecked")
        final Callable<String> callable = mock(Callable.class);

        final String expected = "success";

        // Given
        given(callable.call()).willReturn("valid", expected);

        // When
        final String actual = methodOverrideAspectWaiter().overrideMethod(callable);

        // Then
        assertThat(actual, is(expected));
        verify(callable, times(2)).call();
    }
}
