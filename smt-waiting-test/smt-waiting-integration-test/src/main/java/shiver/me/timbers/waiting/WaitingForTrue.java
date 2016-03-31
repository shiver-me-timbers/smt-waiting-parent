package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;

public interface WaitingForTrue {

    <T> T waitForTrueMethod(Callable<T> callable) throws Exception;
}
