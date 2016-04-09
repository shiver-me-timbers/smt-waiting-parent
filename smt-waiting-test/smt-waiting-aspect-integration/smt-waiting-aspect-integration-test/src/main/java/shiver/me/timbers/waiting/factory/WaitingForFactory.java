package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.ResultValidator;
import shiver.me.timbers.waiting.execution.WaitingFor;
import shiver.me.timbers.waiting.validation.ValidResult;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WaitingForFactory {

    private final LookupFactory<WaitingFor> lookupFactory;

    public WaitingForFactory(
        WaitingFor canWaitUntilValidWaitingForClass,
        WaitingFor canWaitUntilTimeoutWaitingForClass
    ) {
        this(new MapLookupFactory<WaitingFor>());
        final ValidResult validator = new ValidResult();
        add(canWaitUntilValidWaitingForClass, 500L, MILLISECONDS, validator);
        add(canWaitUntilTimeoutWaitingForClass, 200L, MILLISECONDS, validator);
    }

    public WaitingForFactory(LookupFactory<WaitingFor> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingFor create(long duration, TimeUnit unit, ResultValidator validator) {
        return lookupFactory.find(duration, unit, validator.getClass());
    }

    public void add(WaitingFor waitingFor, Long duration, TimeUnit unit, ResultValidator validator) {
        lookupFactory.add(waitingFor, duration, unit, validator.getClass());
    }
}
