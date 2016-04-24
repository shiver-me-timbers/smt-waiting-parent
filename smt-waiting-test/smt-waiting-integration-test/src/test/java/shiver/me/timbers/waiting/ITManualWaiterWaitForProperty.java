package shiver.me.timbers.waiting;

import org.junit.Rule;
import shiver.me.timbers.waiting.execution.ManualClearWaitingFor;
import shiver.me.timbers.waiting.execution.ManualWaitingDefaults;
import shiver.me.timbers.waiting.execution.ManualWaitingFor;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingFor;
import shiver.me.timbers.waiting.property.SystemPropertyManager;
import shiver.me.timbers.waiting.test.WaitingPropertyRule;

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
    protected WaitingFor addWaitFor(long duration, TimeUnit unit, ResultValidator validator) {
        return new ManualWaitingFor(duration, unit, validator);
    }

    @Override
    protected WaitingFor clearThenAddWaitFor(
        long duration,
        TimeUnit unit,
        boolean clearWaitFor,
        ResultValidator validator
    ) {
        return new ManualClearWaitingFor(duration, unit, clearWaitFor, validator);
    }
}
