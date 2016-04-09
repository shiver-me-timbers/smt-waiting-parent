package shiver.me.timbers.waiting.execution;

import java.util.concurrent.Callable;

public interface WaitingExclude {

    <T> T excludeMethod(Callable<T> callable) throws Exception;
}
