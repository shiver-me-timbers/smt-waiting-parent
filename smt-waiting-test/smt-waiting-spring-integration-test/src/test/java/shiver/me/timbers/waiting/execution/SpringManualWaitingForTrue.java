package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.SpringOptions;
import shiver.me.timbers.waiting.SpringWaiter;

import java.util.concurrent.TimeUnit;

public class SpringManualWaitingForTrue extends ManualWaitingForTrue<SpringOptions, SpringWaiter>
    implements WaitingForTrue {

    public SpringManualWaitingForTrue(long duration, TimeUnit unit, boolean isTrue) {
        super(duration, unit, isTrue);
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
