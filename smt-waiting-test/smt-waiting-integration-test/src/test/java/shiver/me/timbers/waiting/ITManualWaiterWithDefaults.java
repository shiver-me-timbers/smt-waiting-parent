package shiver.me.timbers.waiting;

import org.junit.Rule;

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
