package shiver.me.timbers.waiting;

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
