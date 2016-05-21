package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanAddExtraWaitingIncludeMethod;
import shiver.me.timbers.waiting.execution.CanIgnoreAllWaitingIncludeMethod;
import shiver.me.timbers.waiting.execution.CanIgnoreWaitingIncludeMethod;

public class WaitingIncludeMethodFactory extends WaitingIncludeFactory {

    public WaitingIncludeMethodFactory() {
        this(
            new CanIgnoreWaitingIncludeMethod(),
            new CanIgnoreAllWaitingIncludeMethod(),
            new CanAddExtraWaitingIncludeMethod()
        );
    }

    public WaitingIncludeMethodFactory(
        CanIgnoreWaitingIncludeMethod canIgnoreWaitingIncludeMethod,
        CanIgnoreAllWaitingIncludeMethod canIgnoreAllWaitingIncludeMethod,
        CanAddExtraWaitingIncludeMethod canAddExtraWaitingIncludeMethod
    ) {
        super(canIgnoreWaitingIncludeMethod, canIgnoreAllWaitingIncludeMethod, canAddExtraWaitingIncludeMethod);
    }
}
