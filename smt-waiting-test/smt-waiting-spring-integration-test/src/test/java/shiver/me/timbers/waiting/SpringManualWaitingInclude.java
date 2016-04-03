package shiver.me.timbers.waiting;

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
