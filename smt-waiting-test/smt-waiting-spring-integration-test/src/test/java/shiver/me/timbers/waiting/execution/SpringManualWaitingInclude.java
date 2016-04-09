package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.SpringOptions;
import shiver.me.timbers.waiting.SpringWaiter;

import java.util.concurrent.TimeUnit;

public class SpringManualWaitingInclude extends ManualWaitingInclude<SpringOptions, SpringWaiter>
    implements WaitingInclude {

    public SpringManualWaitingInclude(long duration, TimeUnit unit, Throwable... includes) {
        super(duration, unit, includes);
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
