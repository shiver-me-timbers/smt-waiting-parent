package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;

class ManualWaitingDefaults implements WaitingDefaults {
    @Override
    public <T> T defaultsMethod(final Callable<T> callable) throws Exception {
        return new Waiter().wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
