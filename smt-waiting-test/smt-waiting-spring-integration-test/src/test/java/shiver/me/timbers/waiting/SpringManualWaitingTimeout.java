package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class SpringManualWaitingTimeout extends ManualWaitingTimeout<SpringOptions, SpringWaiter>
    implements WaitingTimeout {

    public SpringManualWaitingTimeout(long duration, TimeUnit unit) {
        super(duration, unit);
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
