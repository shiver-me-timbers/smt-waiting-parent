package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class WaitingForFactory {

    private final LookupFactory<WaitingFor> lookupFactory;

    public WaitingForFactory() {
        this(new MapLookupFactory<WaitingFor>());
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
