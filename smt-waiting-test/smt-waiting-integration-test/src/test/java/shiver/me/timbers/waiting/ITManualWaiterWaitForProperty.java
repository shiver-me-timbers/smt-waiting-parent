package shiver.me.timbers.waiting;

import org.junit.Rule;
import shiver.me.timbers.waiting.execution.ManualWaitingDefaults;
import shiver.me.timbers.waiting.execution.ManualWaitingFor;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingFor;
import shiver.me.timbers.waiting.property.SystemPropertyManager;
import shiver.me.timbers.waiting.test.WaitingPropertyRule;
import shiver.me.timbers.waiting.validation.SuccessResult;

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
