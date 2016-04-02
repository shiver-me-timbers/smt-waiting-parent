package shiver.me.timbers.waiting;

import org.junit.Rule;

import java.util.concurrent.TimeUnit;

public class ITManualWaiterExcludeProperty extends AbstractITWaiterExcludeProperty {

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
    protected WaitingExclude addExclude(long duration, TimeUnit unit, Throwable exclude) {
        return new ManualWaitingExclude(duration, unit, exclude);
    }
}
