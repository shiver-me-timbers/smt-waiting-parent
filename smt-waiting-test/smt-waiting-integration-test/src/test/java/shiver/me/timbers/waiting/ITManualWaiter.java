package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.ManualWaitingExclude;
import shiver.me.timbers.waiting.execution.ManualWaitingExcludeWithInclude;
import shiver.me.timbers.waiting.execution.ManualWaitingFor;
import shiver.me.timbers.waiting.execution.ManualWaitingForNotNull;
import shiver.me.timbers.waiting.execution.ManualWaitingForTrue;
import shiver.me.timbers.waiting.execution.ManualWaitingInclude;
import shiver.me.timbers.waiting.execution.ManualWaitingIncludeWithExclude;
import shiver.me.timbers.waiting.execution.ManualWaitingInterval;
import shiver.me.timbers.waiting.execution.ManualWaitingTimeout;
import shiver.me.timbers.waiting.execution.WaitingExclude;
import shiver.me.timbers.waiting.execution.WaitingFor;
import shiver.me.timbers.waiting.execution.WaitingForNotNull;
import shiver.me.timbers.waiting.execution.WaitingForTrue;
import shiver.me.timbers.waiting.execution.WaitingInclude;
import shiver.me.timbers.waiting.execution.WaitingInterval;
import shiver.me.timbers.waiting.execution.WaitingTimeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ITManualWaiter extends AbstractITWaiter {

    @Override
    public WaitingInterval interval(final long duration, final TimeUnit unit) {
        return new ManualWaitingInterval(duration, unit);
    }

    @Override
    public WaitingTimeout timeout(final long duration, final TimeUnit unit) {
        return new ManualWaitingTimeout(duration, unit);
    }

    @Override
    public WaitingFor waitFor(final long duration, final TimeUnit unit, final ResultValidator validator) {
        return new ManualWaitingFor(duration, unit, validator);
    }

    @Override
    public WaitingForNotNull waitForNotNull(final long duration, final TimeUnit unit, final boolean isNotNull) {
        return new ManualWaitingForNotNull(duration, unit, isNotNull);
    }

    @Override
    public WaitingForTrue waitForTrue(final long duration, final TimeUnit unit, final boolean isTrue) {
        return new ManualWaitingForTrue(duration, unit, isTrue);
    }

    @Override
    public WaitingInclude includes(final long duration, final TimeUnit unit, final Throwable... includes) {
        return new ManualWaitingInclude(duration, unit, includes);
    }

    @Override
    public WaitingInclude includesWithExcludes(
        final long duration,
        final TimeUnit unit,
        final List<Throwable> includes,
        final List<Throwable> excludes
    ) {
        return new ManualWaitingIncludeWithExclude(duration, unit, includes, excludes);
    }

    @Override
    public WaitingExclude excludes(final long duration, final TimeUnit unit, final Throwable... excludes) {
        return new ManualWaitingExclude(duration, unit, excludes);
    }

    @Override
    public WaitingExclude excludesWithIncludes(long duration, TimeUnit unit, List<Throwable> excludes, List<Throwable> includes) {
        return new ManualWaitingExcludeWithInclude(duration, unit, excludes, includes);
    }

}
