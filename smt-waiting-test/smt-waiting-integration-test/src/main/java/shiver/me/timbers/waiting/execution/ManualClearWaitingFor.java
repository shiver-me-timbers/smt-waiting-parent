package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Options;
import shiver.me.timbers.waiting.ResultValidator;
import shiver.me.timbers.waiting.Until;
import shiver.me.timbers.waiting.Waiter;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ManualClearWaitingFor<O extends Options, W extends Waiter> extends WaiterCreator<O, W>
    implements WaitingFor {

    private final long duration;
    private final TimeUnit unit;
    private final boolean clearWaitFor;
    private final ResultValidator validator;

    public ManualClearWaitingFor(long duration, TimeUnit unit, boolean clearWaitFor, ResultValidator validator) {
        this.duration = duration;
        this.unit = unit;
        this.clearWaitFor = clearWaitFor;
        this.validator = validator;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T waitForMethod(final Callable<T> callable) throws Exception {
        return waiter((O) options().withTimeout(duration, unit).clearWaitFor(clearWaitFor).waitFor(validator))
            .wait(new Until<T>() {
                @Override
                public T success() throws Throwable {
                    return callable.call();
                }
            });
    }
}
