package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;

public interface WaitingInclude {

    <T> T includeMethod(Callable<T> callable) throws Exception;
}
