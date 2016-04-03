package shiver.me.timbers.waiting;

public class ITManualSpringWaiterWithDefaults extends AbstractITSpringWaiterWithDefaults {

    @Override
    public WaitingDefaults withDefaults(final boolean defaults) {
        return new SpringManualWaitingWithDefaults(defaults);
    }
}
