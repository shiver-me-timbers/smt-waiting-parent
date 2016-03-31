package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public interface ITWaiterWaitForNotNull {

    WaitingForNotNull waitForNotNull(long duration, TimeUnit unit, boolean isNotNull);

    void Can_wait_for_a_non_null_value() throws Throwable;

    void Can_wait_until_time_out_for_non_null_when_null_always_returned() throws Throwable;
}
