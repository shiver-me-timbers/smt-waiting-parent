package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ManualWaitingInterval<O extends Options, W extends Waiter> extends WaiterCreater<O, W>
    implements WaitingInterval {

    private final long duration;
    private final TimeUnit unit;

    public ManualWaitingInterval(long duration, TimeUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T intervalMethod(final Callable<T> callable) throws Exception {
        return waiter((O) options().withInterval(duration, unit)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
