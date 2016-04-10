package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingDefaultsClass;
import shiver.me.timbers.waiting.execution.WaitingWithDefaultsClass;

public class WaitingWithDefaultsClassFactory extends WaitingWithDefaultsFactory {

    public WaitingWithDefaultsClassFactory() {
        this(new WaitingWithDefaultsClass(), new WaitingDefaultsClass());
    }

    public WaitingWithDefaultsClassFactory(
        WaitingWithDefaultsClass waitingWithDefaults,
        WaitingDefaultsClass waitingWithoutDefaults
    ) {
        super(waitingWithDefaults, waitingWithoutDefaults);
    }
}
