package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanAddExtraWaitingIncludeClass;
import shiver.me.timbers.waiting.execution.CanIgnoreAllWaitingIncludeClass;
import shiver.me.timbers.waiting.execution.CanIgnoreWaitingIncludeClass;

public class WaitingIncludeClassFactory extends WaitingIncludeFactory {

    public WaitingIncludeClassFactory() {
        this(
            new CanIgnoreWaitingIncludeClass(),
            new CanIgnoreAllWaitingIncludeClass(),
            new CanAddExtraWaitingIncludeClass()
        );
    }

    public WaitingIncludeClassFactory(
        CanIgnoreWaitingIncludeClass canIgnoreWaitingIncludeClass,
        CanIgnoreAllWaitingIncludeClass canIgnoreAllWaitingIncludeClass,
        CanAddExtraWaitingIncludeClass canAddExtraWaitingIncludeClass
    ) {
        super(canIgnoreWaitingIncludeClass, canIgnoreAllWaitingIncludeClass, canAddExtraWaitingIncludeClass);
    }
}
