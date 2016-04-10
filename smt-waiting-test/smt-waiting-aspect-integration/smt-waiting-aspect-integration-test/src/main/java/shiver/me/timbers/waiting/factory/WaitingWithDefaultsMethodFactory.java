package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingDefaultsMethod;
import shiver.me.timbers.waiting.execution.WaitingWithDefaultsMethod;

public class WaitingWithDefaultsMethodFactory extends WaitingWithDefaultsFactory {

    public WaitingWithDefaultsMethodFactory() {
        this(new WaitingWithDefaultsMethod(), new WaitingDefaultsMethod());
    }

    public WaitingWithDefaultsMethodFactory(
        WaitingWithDefaultsMethod waitingWithDefaults,
        WaitingDefaultsMethod waitingWithoutDefaults
    ) {
        super(waitingWithDefaults, waitingWithoutDefaults);
    }
}
