package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.SpringOptions;
import shiver.me.timbers.waiting.SpringWaiter;

import java.util.concurrent.TimeUnit;

public class SpringManualWaitingExclude extends ManualWaitingExclude<SpringOptions, SpringWaiter> {

    public SpringManualWaitingExclude(long duration, TimeUnit unit, Throwable... excludes) {
        super(duration, unit, excludes);
    }

    @Override
    public SpringOptions options() {
        return new SpringOptions();
    }

    @Override
    public SpringWaiter waiter(SpringOptions options) {
        return new SpringWaiter(options);
    }
}
