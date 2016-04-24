package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.ResultValidator;
import shiver.me.timbers.waiting.SpringOptions;
import shiver.me.timbers.waiting.SpringWaiter;

import java.util.concurrent.TimeUnit;

public class SpringManualClearWaitingFor extends ManualClearWaitingFor<SpringOptions, SpringWaiter>
    implements WaitingFor {

    public SpringManualClearWaitingFor(long duration, TimeUnit unit, boolean clearWaitFor, ResultValidator validator) {
        super(duration, unit, clearWaitFor, validator);
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
