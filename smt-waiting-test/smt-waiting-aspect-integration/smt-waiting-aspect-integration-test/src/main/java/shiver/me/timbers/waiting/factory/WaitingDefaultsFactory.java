package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingDefaults;

public class WaitingDefaultsFactory {

    private final WaitingDefaults waitingDefaults;

    public WaitingDefaultsFactory(WaitingDefaults waitingDefaults) {
        this.waitingDefaults = waitingDefaults;
    }

    public WaitingDefaults create() {
        return waitingDefaults;
    }
}
