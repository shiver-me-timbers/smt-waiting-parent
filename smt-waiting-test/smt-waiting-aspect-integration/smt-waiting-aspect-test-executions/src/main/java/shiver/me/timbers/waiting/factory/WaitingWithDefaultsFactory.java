package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingDefaults;

public class WaitingWithDefaultsFactory {

    private final LookupFactory<WaitingDefaults> lookupFactory;

    public WaitingWithDefaultsFactory(WaitingDefaults waitingWithDefaults, WaitingDefaults waitingWithoutDefaults) {
        this(new MapLookupFactory<WaitingDefaults>());
        add(waitingWithDefaults, true);
        add(waitingWithoutDefaults, false);
    }

    public WaitingWithDefaultsFactory(LookupFactory<WaitingDefaults> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingDefaults create(boolean defaults) {
        return lookupFactory.find(defaults);
    }

    public void add(WaitingDefaults waitingDefaults, Boolean defaults) {
        lookupFactory.add(waitingDefaults, defaults);
    }
}
