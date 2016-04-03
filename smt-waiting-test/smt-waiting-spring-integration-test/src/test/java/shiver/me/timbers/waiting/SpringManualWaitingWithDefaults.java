package shiver.me.timbers.waiting;

public class SpringManualWaitingWithDefaults extends ManualWaitingWithDefaults<SpringOptions, SpringWaiter>
    implements WaitingDefaults {

    public SpringManualWaitingWithDefaults(boolean defaults) {
        super(defaults);
    }

    @Override
    public SpringOptions options() {
        return new SpringOptions();
    }

    @Override
    public SpringWaiter waiter(SpringOptions options) {
        return new SpringWaiter(options);
    }
}
