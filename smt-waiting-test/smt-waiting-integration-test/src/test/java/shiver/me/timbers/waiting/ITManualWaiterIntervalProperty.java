package shiver.me.timbers.waiting;

import org.junit.Rule;

import java.util.concurrent.TimeUnit;

public class ITManualWaiterIntervalProperty extends AbstractITWaiterIntervalProperty {

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
    protected WaitingInterval overrideInterval(final long duration, final TimeUnit unit) {
        return new ManualWaitingInterval(duration, unit);
    }
}
