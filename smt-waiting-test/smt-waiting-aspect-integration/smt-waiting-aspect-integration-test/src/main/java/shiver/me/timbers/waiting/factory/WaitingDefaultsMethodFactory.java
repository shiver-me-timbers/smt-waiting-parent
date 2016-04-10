package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingDefaultsMethod;

public class WaitingDefaultsMethodFactory extends WaitingDefaultsFactory {

    public WaitingDefaultsMethodFactory() {
        super(new WaitingDefaultsMethod());
    }

    public WaitingDefaultsMethodFactory(WaitingDefaultsMethod waitingDefaults) {
        super(waitingDefaults);
    }
}
