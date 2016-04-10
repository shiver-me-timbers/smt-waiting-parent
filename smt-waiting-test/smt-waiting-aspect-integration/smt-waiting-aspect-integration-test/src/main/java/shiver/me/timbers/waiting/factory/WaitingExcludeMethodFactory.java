package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanAddExtraWaitingExcludeMethod;
import shiver.me.timbers.waiting.execution.CanIgnoreWaitingExcludeMethod;
import shiver.me.timbers.waiting.execution.CannotIgnoreWaitingExcludeMethod;

public class WaitingExcludeMethodFactory extends WaitingExcludeFactory {

    public WaitingExcludeMethodFactory() {
        this(
            new CannotIgnoreWaitingExcludeMethod(),
            new CanIgnoreWaitingExcludeMethod(),
            new CanAddExtraWaitingExcludeMethod()
        );
    }

    public WaitingExcludeMethodFactory(
        CannotIgnoreWaitingExcludeMethod cannotIgnoreWaitingExcludeMethod,
        CanIgnoreWaitingExcludeMethod canIgnoreWaitingExcludeMethod,
        CanAddExtraWaitingExcludeMethod canAddExtraWaitingExcludeMethod
    ) {
        super(cannotIgnoreWaitingExcludeMethod, canIgnoreWaitingExcludeMethod, canAddExtraWaitingExcludeMethod);
    }
}
