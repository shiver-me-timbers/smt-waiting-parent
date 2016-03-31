package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class ManualWaitingForNotNull implements WaitingForNotNull {

    private final long duration;
    private final TimeUnit unit;
    private final boolean notNull;

    public ManualWaitingForNotNull(long duration, TimeUnit unit, boolean notNull) {
        this.duration = duration;
        this.unit = unit;
        this.notNull = notNull;
    }

    @Override
    public <T> T waitForNotNull(final Callable<T> callable) {
        return new Waiter(new Options().withTimeout(duration, unit).willWaitForNotNull(notNull)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
