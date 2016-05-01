package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.SpringOptions;
import shiver.me.timbers.waiting.SpringWaiter;

import java.util.concurrent.TimeUnit;

public class SpringManualClearWaitingInclude extends ManualClearWaitingInclude<SpringOptions, SpringWaiter> {

    public SpringManualClearWaitingInclude(long duration, TimeUnit unit, boolean clearInclude, Throwable include) {
        super(duration, unit, clearInclude, include);
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
