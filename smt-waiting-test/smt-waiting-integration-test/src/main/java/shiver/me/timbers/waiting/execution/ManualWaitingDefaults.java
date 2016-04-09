package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Options;
import shiver.me.timbers.waiting.Until;
import shiver.me.timbers.waiting.Waiter;

import java.util.concurrent.Callable;

public class ManualWaitingDefaults<O extends Options, W extends Waiter> extends WaiterCreator<O, W>
    implements WaitingDefaults {

    @Override
    public <T> T defaultsMethod(final Callable<T> callable) throws Exception {
        return waiter().wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
