package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ManualWaitingForTrue<O extends Options, W extends Waiter> extends WaiterCreator<O, W>
    implements WaitingForTrue {

    private final long duration;
    private final TimeUnit unit;
    private final boolean isTrue;

    public ManualWaitingForTrue(long duration, TimeUnit unit, boolean isTrue) {
        this.duration = duration;
        this.unit = unit;
        this.isTrue = isTrue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T waitForTrueMethod(final Callable<T> callable) {
        return waiter((O) options().withTimeout(duration, unit).willWaitForTrue(isTrue)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
