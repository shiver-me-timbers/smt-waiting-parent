package shiver.me.timbers.waiting;

import org.junit.Rule;

import java.util.concurrent.TimeUnit;

public class ITManualWaiterWaitForTrueProperty extends AbstractITWaiterWaitForTrueProperty {

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
    protected WaitingForTrue overrideWaitForTrue(long duration, TimeUnit unit, boolean isTrue) {
        return new ManualWaitingForTrue(duration, unit, isTrue);
    }
}
