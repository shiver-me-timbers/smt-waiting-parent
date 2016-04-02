package shiver.me.timbers.waiting;

import org.junit.Rule;

import java.util.concurrent.TimeUnit;

public class ITManualWaiterIncludeProperty extends AbstractITWaiterIncludeProperty {

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
    protected WaitingInclude addInclude(long duration, TimeUnit unit, Throwable include) {
        return new ManualWaitingInclude(duration, unit, include);
    }
}
