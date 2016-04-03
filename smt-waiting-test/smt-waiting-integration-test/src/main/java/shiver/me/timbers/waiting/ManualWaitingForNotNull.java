package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ManualWaitingForNotNull<O extends Options, W extends Waiter> extends WaiterCreator<O, W>
    implements WaitingForNotNull {

    private final long duration;
    private final TimeUnit unit;
    private final boolean notNull;

    public ManualWaitingForNotNull(long duration, TimeUnit unit, boolean notNull) {
        this.duration = duration;
        this.unit = unit;
        this.notNull = notNull;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T waitForNotNullMethod(final Callable<T> callable) {
        return waiter((O) options().withTimeout(duration, unit).willWaitForNotNull(notNull)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
