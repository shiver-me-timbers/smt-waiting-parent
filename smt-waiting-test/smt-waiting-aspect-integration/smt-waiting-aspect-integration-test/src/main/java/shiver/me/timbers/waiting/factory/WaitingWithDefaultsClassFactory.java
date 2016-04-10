package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingDefaultsClass;
import shiver.me.timbers.waiting.execution.WaitingWithDefaultsClass;

public class WaitingWithDefaultsClassFactory extends WaitingWithDefaultsFactory {

    public WaitingWithDefaultsClassFactory() {
        this(new WaitingDefaultsClass(), new WaitingWithDefaultsClass());
    }

    public WaitingWithDefaultsClassFactory(
        WaitingDefaultsClass waitingWithDefaults,
        WaitingWithDefaultsClass waitingWithoutDefaults
    ) {
        super(waitingWithDefaults, waitingWithoutDefaults);
    }
}
