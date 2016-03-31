package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public interface ITWaiterWaitForNotNull {

    WaitingForNotNull waitForNotNull(long duration, TimeUnit unit, boolean isNotNull);
}
