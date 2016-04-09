package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.SpringManualWaitingDefaults;
import shiver.me.timbers.waiting.execution.SpringManualWaitingFor;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingFor;
import shiver.me.timbers.waiting.validation.SuccessResult;

import java.util.concurrent.TimeUnit;

public class ITManualSpringWaiterWaitForProperty extends AbstractITSpringWaiterWaitForProperty {

    @Override
    public WaitingDefaults defaults() {
        return new SpringManualWaitingDefaults();
    }

    @Override
    protected WaitingFor addWaitFor(long duration, TimeUnit unit, SuccessResult successResult) {
        return new SpringManualWaitingFor(duration, unit, successResult);
    }
}
