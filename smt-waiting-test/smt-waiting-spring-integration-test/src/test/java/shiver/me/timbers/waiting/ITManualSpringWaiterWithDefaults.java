package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.SpringManualWaitingWithDefaults;
import shiver.me.timbers.waiting.execution.WaitingDefaults;

public class ITManualSpringWaiterWithDefaults extends AbstractITSpringWaiterWithDefaults {

    @Override
    public WaitingDefaults withDefaults(final boolean defaults) {
        return new SpringManualWaitingWithDefaults(defaults);
    }
}
