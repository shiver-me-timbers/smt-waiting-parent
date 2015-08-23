package shiver.me.timbers.waiting;

public class ITCompileTimeWaiterAspectMethods extends ITWaiterAspect {

    private final WaitingComponent component = new WaitingMethodsComponent();

    @Override
    protected WaitingDefaultsComponent defaultsComponent() {
        return component;
    }

    @Override
    protected WaitingDurationComponent durationComponent() {
        return component;
    }

    @Override
    protected WaitingForComponent waitForComponent() {
        return component;
    }

    @Override
    protected WaitingForTrueComponent waitForTrueComponent() {
        return component;
    }

    @Override
    protected WaitingForNotNullComponent waitForNotNullComponent() {
        return component;
    }

    @Override
    protected WaitingIntervalComponent intervalComponent() {
        return component;
    }
}
