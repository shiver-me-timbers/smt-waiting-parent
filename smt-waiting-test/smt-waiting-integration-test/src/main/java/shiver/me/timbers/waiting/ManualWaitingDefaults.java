package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;

public class ManualWaitingDefaults extends WaiterCreater implements WaitingDefaults {

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
