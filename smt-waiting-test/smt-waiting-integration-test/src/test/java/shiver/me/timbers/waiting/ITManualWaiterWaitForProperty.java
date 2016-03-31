package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class ITManualWaiterWaitForProperty extends AbstractITWaiterWaitForProperty {

    @Override
    public WaitingDefaults defaults() {
        return new ManualWaitingDefaults();
    }

    @Override
    protected WaitingFor addWaitFor(long duration, TimeUnit unit, SuccessResult successResult) {
        return new ManualWaitingFor(duration, unit, successResult);
    }
}
