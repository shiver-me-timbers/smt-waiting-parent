package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Wait;
import shiver.me.timbers.waiting.validation.SuccessResult;

import java.util.concurrent.Callable;

/**
 * @author Karl Bennett
 */
public interface WaitingClassLevelOverride {
    @Wait(waitFor = SuccessResult.class)
    <T> T overrideMethod(Callable<T> callable) throws Exception;
}
