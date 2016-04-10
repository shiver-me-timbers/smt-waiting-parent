package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingDefaultsMethod;
import shiver.me.timbers.waiting.execution.WaitingWithDefaultsMethod;

public class WaitingWithDefaultsMethodFactory extends WaitingWithDefaultsFactory {

    public WaitingWithDefaultsMethodFactory() {
        this(new WaitingDefaultsMethod(), new WaitingWithDefaultsMethod());
    }

    public WaitingWithDefaultsMethodFactory(
        WaitingDefaultsMethod waitingWithDefaults,
        WaitingWithDefaultsMethod waitingWithoutDefaults
    ) {
        super(waitingWithDefaults, waitingWithoutDefaults);
    }
}
