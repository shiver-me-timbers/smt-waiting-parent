package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;

public class ITManualWaiterWithDefaults extends AbstractITWaiterWithDefaults {

    @Override
    public WaitingDefaults withDefaults(final boolean defaults) {
        return new WaitingDefaults() {
            @Override
            public <T> T defaultsMethod(final Callable<T> callable) throws Exception {
                return new Waiter(new Options().withDefaults(defaults)).wait(new Until<T>() {
                    @Override
                    public T success() throws Throwable {
                        return callable.call();
                    }
                });
            }
        };
    }
}
