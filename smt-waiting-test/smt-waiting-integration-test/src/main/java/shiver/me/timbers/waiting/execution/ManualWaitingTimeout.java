package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Options;
import shiver.me.timbers.waiting.Until;
import shiver.me.timbers.waiting.Waiter;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ManualWaitingTimeout<O extends Options, W extends Waiter> extends WaiterCreator<O, W>
    implements WaitingTimeout {

    private final long duration;
    private final TimeUnit unit;

    public ManualWaitingTimeout(long duration, TimeUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T timeoutMethod(final Callable<T> callable) {
        return waiter((O) options().withTimeout(duration, unit)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
