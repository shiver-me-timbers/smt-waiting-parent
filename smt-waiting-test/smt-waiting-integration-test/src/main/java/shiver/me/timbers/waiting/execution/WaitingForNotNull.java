package shiver.me.timbers.waiting.execution;

import java.util.concurrent.Callable;

public interface WaitingForNotNull {

    <T> T waitForNotNullMethod(Callable<T> callable) throws Exception;
}
