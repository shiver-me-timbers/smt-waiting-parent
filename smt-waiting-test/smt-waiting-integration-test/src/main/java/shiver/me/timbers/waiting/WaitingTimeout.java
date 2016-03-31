package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;

public interface WaitingTimeout {

    <T> T timeoutMethod(Callable<T> callable) throws Exception;
}
