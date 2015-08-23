package shiver.me.timbers.waiting;

public class ITCompileTimeWaiterAspectClass extends ITWaiterAspect {

    @Override
    protected WaitingDefaultsComponent defaultsComponent() {
        return new WaitingDefaultsClassComponent();
    }

    @Override
    protected WaitingDurationComponent durationComponent() {
        return new WaitingDurationClassComponent();
    }

    @Override
    protected WaitingForComponent waitForComponent() {
        return new WaitingForClassComponent();
    }

    @Override
    protected WaitingForTrueComponent waitForTrueComponent() {
        return new WaitingForTrueClassComponent();
    }

    @Override
    protected WaitingForNotNullComponent waitForNotNullComponent() {
        return new WaitingForNotNullClassComponent();
    }

    @Override
    protected WaitingIntervalComponent intervalComponent() {
        return new WaitingIntervalClassComponent();
    }
}
