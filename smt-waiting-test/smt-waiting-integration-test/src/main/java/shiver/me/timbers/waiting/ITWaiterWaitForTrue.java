package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.WaitingForTrue;

import java.util.concurrent.TimeUnit;

public interface ITWaiterWaitForTrue {

    WaitingForTrue waitForTrue(long duration, TimeUnit unit, boolean isTrue);

    void Can_wait_until_true_is_returned() throws Throwable;

    void Can_wait_until_time_out_for_true_when_false_always_returned() throws Throwable;

    void Can_wait_until_time_out_for_true_when_null_always_returned() throws Throwable;
}
