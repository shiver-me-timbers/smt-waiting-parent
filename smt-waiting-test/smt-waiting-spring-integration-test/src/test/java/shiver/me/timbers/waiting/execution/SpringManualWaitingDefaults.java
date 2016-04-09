package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.SpringOptions;
import shiver.me.timbers.waiting.SpringWaiter;

public class SpringManualWaitingDefaults extends ManualWaitingDefaults<SpringOptions, SpringWaiter> {

    @Override
    public SpringWaiter waiter() {
        return new SpringWaiter();
    }
}
