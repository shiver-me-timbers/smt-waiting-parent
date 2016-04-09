package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.SpringOptions;
import shiver.me.timbers.waiting.SpringWaiter;

import java.util.concurrent.TimeUnit;

public class SpringManualWaitingInterval extends ManualWaitingInterval<SpringOptions, SpringWaiter>
    implements WaitingInterval {

    public SpringManualWaitingInterval(long duration, TimeUnit unit) {
        super(duration, unit);
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
