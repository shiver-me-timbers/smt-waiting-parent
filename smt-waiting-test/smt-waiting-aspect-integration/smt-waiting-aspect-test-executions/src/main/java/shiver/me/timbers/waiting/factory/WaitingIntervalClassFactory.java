package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanChangeWaitingIntervalClass;

public class WaitingIntervalClassFactory extends WaitingIntervalFactory {

    public WaitingIntervalClassFactory() {
        this(new CanChangeWaitingIntervalClass());
    }

    public WaitingIntervalClassFactory(CanChangeWaitingIntervalClass canChangeWaitingIntervalClass) {
        super(canChangeWaitingIntervalClass);
    }
}
