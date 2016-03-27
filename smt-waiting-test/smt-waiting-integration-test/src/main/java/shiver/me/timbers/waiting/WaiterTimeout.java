package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;

public interface WaiterTimeout {

    <T> T timeoutMethod(Callable<T> callable) throws Exception;
}
