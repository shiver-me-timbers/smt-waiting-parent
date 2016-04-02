package shiver.me.timbers.waiting;

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
    public WaitingInclude include(final long duration, final TimeUnit unit, final Throwable... includes) {
        return new ManualWaitingInclude(duration, unit, includes);
    }

    @Override
    public WaitingInclude includeWithExclude(
        final long duration,
        final TimeUnit unit,
        final List<Throwable> includes,
        final List<Throwable> excludes
    ) {
        return new ManualWaitingIncludeWithExclude(duration, unit, includes, excludes);
    }

}