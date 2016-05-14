package shiver.me.timbers.waiting;

import org.junit.Rule;
import shiver.me.timbers.waiting.execution.ManualClearWaitingInclude;
import shiver.me.timbers.waiting.execution.ManualWaitingDefaults;
import shiver.me.timbers.waiting.execution.ManualWaitingInclude;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingInclude;
import shiver.me.timbers.waiting.property.SystemPropertyManager;
import shiver.me.timbers.waiting.test.WaitingPropertyRule;

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

    @Override
    protected WaitingInclude clearThenAddInclude(long duration, TimeUnit unit, boolean clearIncludes, Throwable include) {
        return new ManualClearWaitingInclude(duration, unit, clearIncludes, include);
    }
}
