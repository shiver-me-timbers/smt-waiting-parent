package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanIgnoreWaitingExcludeClass;
import shiver.me.timbers.waiting.execution.CannotIgnoreWaitingExcludeClass;

public class WaitingExcludeClassFactory extends WaitingExcludeFactory {

    public WaitingExcludeClassFactory() {
        this(new CannotIgnoreWaitingExcludeClass(), new CanIgnoreWaitingExcludeClass());
    }

    public WaitingExcludeClassFactory(
        CannotIgnoreWaitingExcludeClass cannotIgnoreWaitingExcludeClass,
        CanIgnoreWaitingExcludeClass canIgnoreWaitingExcludeClass
    ) {
        super(cannotIgnoreWaitingExcludeClass, canIgnoreWaitingExcludeClass);
    }
}
