package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Options;
import shiver.me.timbers.waiting.ResultValidator;
import shiver.me.timbers.waiting.Until;
import shiver.me.timbers.waiting.Waiter;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ManualWaitingFor<O extends Options, W extends Waiter> extends WaiterCreator<O, W> implements WaitingFor {

    private final long duration;
    private final TimeUnit unit;
    private final ResultValidator validator;

    public ManualWaitingFor(long duration, TimeUnit unit, ResultValidator validator) {
        this.duration = duration;
        this.unit = unit;
        this.validator = validator;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T waitForMethod(final Callable<T> callable) {
        return waiter((O) options().withTimeout(duration, unit).waitFor(validator)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
