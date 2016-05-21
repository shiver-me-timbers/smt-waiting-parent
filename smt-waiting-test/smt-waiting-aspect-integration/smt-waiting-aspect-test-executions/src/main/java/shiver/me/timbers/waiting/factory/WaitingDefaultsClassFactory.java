package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingDefaultsClass;

public class WaitingDefaultsClassFactory extends WaitingDefaultsFactory {

    public WaitingDefaultsClassFactory() {
        super(new WaitingDefaultsClass());
    }

    public WaitingDefaultsClassFactory(WaitingDefaultsClass waitingDefaults) {
        super(waitingDefaults);
    }
}
