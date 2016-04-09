package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.WaitingExclude;
import shiver.me.timbers.waiting.execution.WaitingFor;
import shiver.me.timbers.waiting.execution.WaitingForNotNull;
import shiver.me.timbers.waiting.execution.WaitingForTrue;
import shiver.me.timbers.waiting.execution.WaitingInclude;
import shiver.me.timbers.waiting.execution.WaitingInterval;
import shiver.me.timbers.waiting.execution.WaitingTimeout;
import shiver.me.timbers.waiting.factory.WaitingExcludeFactory;
import shiver.me.timbers.waiting.factory.WaitingExcludesWithIncludesFactory;
import shiver.me.timbers.waiting.factory.WaitingForFactory;
import shiver.me.timbers.waiting.factory.WaitingForNotNullFactory;
import shiver.me.timbers.waiting.factory.WaitingForTrueFactory;
import shiver.me.timbers.waiting.factory.WaitingIncludeFactory;
import shiver.me.timbers.waiting.factory.WaitingIncludesWithExcludesFactory;
import shiver.me.timbers.waiting.factory.WaitingIntervalFactory;
import shiver.me.timbers.waiting.factory.WaitingTimeoutFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractITAspectWaiter extends AbstractITWaiter {

    protected abstract WaitingIntervalFactory intervalFactory();

    protected abstract WaitingTimeoutFactory timeoutFactory();

    protected abstract WaitingForFactory waitForFactory();

    protected abstract WaitingForNotNullFactory waitForNotNullFactory();

    protected abstract WaitingForTrueFactory waitForTrueFactory();

    protected abstract WaitingIncludeFactory includesFactory();

    protected abstract WaitingIncludesWithExcludesFactory includesWithExcludesFactory();

    protected abstract WaitingExcludeFactory excludesFactory();

    protected abstract WaitingExcludesWithIncludesFactory excludesWithIncludesFactory();

    @Override
    public WaitingInterval interval(long duration, TimeUnit unit) {
        return intervalFactory().create(duration, unit);
    }

    @Override
    public WaitingTimeout timeout(long duration, TimeUnit unit) {
        return timeoutFactory().create(duration, unit);
    }

    @Override
    public WaitingFor waitFor(long duration, TimeUnit unit, ResultValidator validator) {
        return waitForFactory().create(duration, unit, validator);
    }

    @Override
    public WaitingForNotNull waitForNotNull(long duration, TimeUnit unit, boolean isNotNull) {
        return waitForNotNullFactory().create(duration, unit, isNotNull);
    }

    @Override
    public WaitingForTrue waitForTrue(long duration, TimeUnit unit, boolean isTrue) {
        return waitForTrueFactory().create(duration, unit, isTrue);
    }

    @Override
    public WaitingInclude includes(long duration, TimeUnit unit, Throwable... includes) {
        return includesFactory().create(duration, unit, includes);
    }

    @Override
    public WaitingInclude includesWithExcludes(long duration, TimeUnit unit, List<Throwable> includes, List<Throwable> excludes) {
        return includesWithExcludesFactory().create(duration, unit, includes, excludes);
    }

    @Override
    public WaitingExclude excludes(long duration, TimeUnit unit, Throwable... excludes) {
        return excludesFactory().create(duration, unit, excludes);
    }

    @Override
    public WaitingExclude excludesWithIncludes(long duration, TimeUnit unit, List<Throwable> excludes, List<Throwable> includes) {
        return excludesWithIncludesFactory().create(duration, unit, excludes, includes);
    }
}
