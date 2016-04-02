package shiver.me.timbers.waiting;

import org.junit.Rule;

import java.util.concurrent.TimeUnit;

public class ITManualWaiterWaitForProperty extends AbstractITWaiterWaitForProperty {

    @Rule
    public WaitingPropertyRule properties = new WaitingPropertyRule(new SystemPropertyManager());

    @Override
    public WaitingPropertyRule properties() {
        return properties;
    }

    @Override
    public WaitingDefaults defaults() {
        return new ManualWaitingDefaults();
    }

    @Override
    protected WaitingFor addWaitFor(long duration, TimeUnit unit, SuccessResult successResult) {
        return new ManualWaitingFor(duration, unit, successResult);
    }
}
