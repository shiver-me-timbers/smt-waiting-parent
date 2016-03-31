package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class ManualWaitingFor implements WaitingFor {

    private final long duration;
    private final TimeUnit unit;
    private final ResultValidator validator;

    public ManualWaitingFor(long duration, TimeUnit unit, ResultValidator validator) {
        this.duration = duration;
        this.unit = unit;
        this.validator = validator;
    }

    @Override
    public <T> T waitForMethod(final Callable<T> callable) {
        return new Waiter(new Options().withTimeout(duration, unit).waitFor(validator)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
