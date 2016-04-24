package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.SpringManualClearWaitingFor;
import shiver.me.timbers.waiting.execution.SpringManualWaitingDefaults;
import shiver.me.timbers.waiting.execution.SpringManualWaitingFor;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingFor;

import java.util.concurrent.TimeUnit;

public class ITManualSpringWaiterWaitForProperty extends AbstractITSpringWaiterWaitForProperty {

    @Override
    public WaitingDefaults defaults() {
        return new SpringManualWaitingDefaults();
    }

    @Override
    protected WaitingFor addWaitFor(long duration, TimeUnit unit, ResultValidator validator) {
        return new SpringManualWaitingFor(duration, unit, validator);
    }

    @Override
    protected WaitingFor clearThenAddWaitFor(
        long duration,
        TimeUnit unit,
        boolean clearWaitFor,
        ResultValidator validator
    ) {
        return new SpringManualClearWaitingFor(duration, unit, clearWaitFor, validator);
    }
}
