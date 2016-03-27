package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class ManualWaiterTimeout implements WaiterTimeout {

    private final long duration;
    private final TimeUnit unit;

    public ManualWaiterTimeout(long duration, TimeUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    @Override
    public <T> T timeoutMethod(final Callable<T> callable) {
        return new Waiter(new Options().withTimeout(duration, unit)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
