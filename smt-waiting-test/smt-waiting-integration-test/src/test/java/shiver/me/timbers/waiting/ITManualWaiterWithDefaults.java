package shiver.me.timbers.waiting;

import org.junit.Rule;
import shiver.me.timbers.waiting.execution.ManualWaitingWithDefaults;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.property.SystemPropertyManager;
import shiver.me.timbers.waiting.test.WaitingPropertyRule;

public class ITManualWaiterWithDefaults extends AbstractITWaiterWithDefaults {

    @Rule
    public WaitingPropertyRule properties = new WaitingPropertyRule(new SystemPropertyManager());

    @Override
    public WaitingPropertyRule properties() {
        return properties;
    }

    @Override
    public WaitingDefaults withDefaults(final boolean defaults) {
        return new ManualWaitingWithDefaults(defaults);
    }
}
