package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Wait;
import shiver.me.timbers.waiting.validation.SuccessResult;
import shiver.me.timbers.waiting.validation.ValidResult;

import java.util.concurrent.Callable;

@Wait(waitFor = ValidResult.class)
public class CanOverrideClassLevelWaitWithMethodLevelWait implements WaitingClassLevelOverride {

    @Wait(waitFor = SuccessResult.class)
    @Override
    public <T> T overrideMethod(Callable<T> callable) throws Exception {
        return callable.call();
    }
}
