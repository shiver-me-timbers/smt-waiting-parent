package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;

public interface WaitingExclude {

    <T> T excludeMethod(Callable<T> callable) throws Exception;
}
