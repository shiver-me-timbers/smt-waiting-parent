package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.SpringOptions;
import shiver.me.timbers.waiting.SpringWaiter;

import java.util.concurrent.TimeUnit;

public class SpringManualClearWaitingExclude extends ManualClearWaitingExclude<SpringOptions, SpringWaiter> {

    public SpringManualClearWaitingExclude(long duration, TimeUnit unit, boolean clearInclude, Throwable exclude) {
        super(duration, unit, clearInclude, exclude);
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
