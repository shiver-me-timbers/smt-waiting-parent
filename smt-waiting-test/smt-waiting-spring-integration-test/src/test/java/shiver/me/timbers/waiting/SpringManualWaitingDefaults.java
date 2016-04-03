package shiver.me.timbers.waiting;

public class SpringManualWaitingDefaults extends ManualWaitingDefaults<SpringOptions, SpringWaiter> {

    @Override
    public SpringWaiter waiter() {
        return new SpringWaiter();
    }
}
