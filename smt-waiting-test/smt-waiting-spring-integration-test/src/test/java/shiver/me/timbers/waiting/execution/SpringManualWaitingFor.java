package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.ResultValidator;
import shiver.me.timbers.waiting.SpringOptions;
import shiver.me.timbers.waiting.SpringWaiter;

import java.util.concurrent.TimeUnit;

public class SpringManualWaitingFor extends ManualWaitingFor<SpringOptions, SpringWaiter> implements WaitingFor {

    public SpringManualWaitingFor(long duration, TimeUnit unit, ResultValidator validator) {
        super(duration, unit, validator);
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
