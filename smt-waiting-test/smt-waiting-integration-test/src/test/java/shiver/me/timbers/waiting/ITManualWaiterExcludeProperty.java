package shiver.me.timbers.waiting;

import org.junit.Rule;
import shiver.me.timbers.waiting.execution.ManualClearWaitingExclude;
import shiver.me.timbers.waiting.execution.ManualWaitingDefaults;
import shiver.me.timbers.waiting.execution.ManualWaitingExclude;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingExclude;
import shiver.me.timbers.waiting.property.SystemPropertyManager;
import shiver.me.timbers.waiting.test.WaitingPropertyRule;

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

    @Override
    protected WaitingExclude clearThenAddExclude(long duration, TimeUnit unit, boolean clearExcludes, Throwable exclude) {
        return new ManualClearWaitingExclude(duration, unit, clearExcludes, exclude);
    }
}
