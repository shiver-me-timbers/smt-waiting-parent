package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class ManualWaitingInterval implements WaitingInterval {

    private final long duration;
    private final TimeUnit unit;

    public ManualWaitingInterval(long duration, TimeUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    @Override
    public <T> T intervalMethod(final Callable<T> callable) throws Exception {
        return new Waiter(new Options().withInterval(duration, unit)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
