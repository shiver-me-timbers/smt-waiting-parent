package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Options;
import shiver.me.timbers.waiting.Until;
import shiver.me.timbers.waiting.Waiter;

import java.util.concurrent.Callable;

public class ManualWaitingWithDefaults<O extends Options, W extends Waiter> extends WaiterCreator<O, W>
    implements WaitingDefaults {

    private final boolean defaults;

    public ManualWaitingWithDefaults(boolean defaults) {
        this.defaults = defaults;
    }

    @Override
    public <T> T defaultsMethod(final Callable<T> callable) throws Exception {
        return new Waiter(new Options().withDefaults(defaults)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
