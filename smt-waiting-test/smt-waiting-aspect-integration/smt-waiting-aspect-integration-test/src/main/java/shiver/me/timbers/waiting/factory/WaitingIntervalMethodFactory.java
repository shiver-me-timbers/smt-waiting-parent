package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanChangeWaitingIntervalMethod;

public class WaitingIntervalMethodFactory extends WaitingIntervalFactory {

    public WaitingIntervalMethodFactory() {
        this(new CanChangeWaitingIntervalMethod());
    }

    public WaitingIntervalMethodFactory(CanChangeWaitingIntervalMethod canChangeWaitingIntervalMethod) {
        super(canChangeWaitingIntervalMethod);
    }
}
