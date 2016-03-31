package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;

public interface WaitingForNotNull {

    <T> T waitForNotNull(Callable<T> callable) throws Exception;
}
