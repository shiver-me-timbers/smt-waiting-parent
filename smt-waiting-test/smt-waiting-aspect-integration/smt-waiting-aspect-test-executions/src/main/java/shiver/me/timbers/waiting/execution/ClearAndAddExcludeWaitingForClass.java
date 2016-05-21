package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Timeout;
import shiver.me.timbers.waiting.Wait;

import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Wait(
    value = @Timeout(duration = 500, unit = MILLISECONDS),
    clearExcludes = true,
    excludes = IllegalStateException.class
)
public class ClearAndAddExcludeWaitingForClass implements WaitingExclude {

    @Override
    public <T> T excludeMethod(Callable<T> callable) throws Exception {
        return callable.call();
    }
}
