package shiver.me.timbers.waiting;

import org.junit.Rule;

import java.util.concurrent.TimeUnit;

public class ITManualWaiterWaitForNotNullProperty extends AbstractITWaiterWaitForNotNullProperty {

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
    protected WaitingForNotNull overrideWaitForNotNull(long duration, TimeUnit unit, boolean isNotNull) {
        return new ManualWaitingForNotNull(duration, unit, isNotNull);
    }
}
