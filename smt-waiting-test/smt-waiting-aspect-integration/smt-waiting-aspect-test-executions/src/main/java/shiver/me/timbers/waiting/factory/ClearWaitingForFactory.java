package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.ResultValidator;
import shiver.me.timbers.waiting.execution.WaitingFor;
import shiver.me.timbers.waiting.validation.SuccessResult;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ClearWaitingForFactory {

    private final LookupFactory<WaitingFor> lookupFactory;

    public ClearWaitingForFactory(WaitingFor clearAndWaitUntilSuccessWaitingFor) {
        this(new MapLookupFactory<WaitingFor>());
        add(clearAndWaitUntilSuccessWaitingFor, 500L, MILLISECONDS, true, new SuccessResult());
    }

    public ClearWaitingForFactory(LookupFactory<WaitingFor> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingFor create(long duration, TimeUnit unit, boolean clearWaitFor, ResultValidator validator) {
        return lookupFactory.find(duration, unit, clearWaitFor, validator.getClass());
    }

    public void add(
        WaitingFor waitingFor,
        Long duration,
        TimeUnit unit,
        Boolean clearWaitFor,
        ResultValidator validator
    ) {
        lookupFactory.add(waitingFor, duration, unit, clearWaitFor, validator.getClass());
    }
}
