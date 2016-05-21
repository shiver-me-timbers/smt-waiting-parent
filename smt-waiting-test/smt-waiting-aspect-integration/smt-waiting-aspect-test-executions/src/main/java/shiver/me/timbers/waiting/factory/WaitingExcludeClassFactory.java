package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanAddExtraWaitingExcludeClass;
import shiver.me.timbers.waiting.execution.CanIgnoreWaitingExcludeClass;
import shiver.me.timbers.waiting.execution.CannotIgnoreWaitingExcludeClass;

public class WaitingExcludeClassFactory extends WaitingExcludeFactory {

    public WaitingExcludeClassFactory() {
        this(
            new CannotIgnoreWaitingExcludeClass(),
            new CanIgnoreWaitingExcludeClass(),
            new CanAddExtraWaitingExcludeClass()
        );
    }

    public WaitingExcludeClassFactory(
        CannotIgnoreWaitingExcludeClass cannotIgnoreWaitingExcludeClass,
        CanIgnoreWaitingExcludeClass canIgnoreWaitingExcludeClass,
        CanAddExtraWaitingExcludeClass canAddExtraWaitingExcludeClass
    ) {
        super(cannotIgnoreWaitingExcludeClass, canIgnoreWaitingExcludeClass, canAddExtraWaitingExcludeClass);
    }
}
