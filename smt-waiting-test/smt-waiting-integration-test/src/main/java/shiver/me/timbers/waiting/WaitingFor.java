package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;

public interface WaitingFor {

    <T> T waitForMethod(Callable<T> callable) throws Exception;
}
