package shiver.me.timbers.waiting;

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
