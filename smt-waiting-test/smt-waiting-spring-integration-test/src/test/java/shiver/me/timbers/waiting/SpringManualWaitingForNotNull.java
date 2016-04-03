package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class SpringManualWaitingForNotNull extends ManualWaitingForNotNull<SpringOptions, SpringWaiter>
    implements WaitingForNotNull {

    public SpringManualWaitingForNotNull(long duration, TimeUnit unit, boolean notNull) {
        super(duration, unit, notNull);
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
