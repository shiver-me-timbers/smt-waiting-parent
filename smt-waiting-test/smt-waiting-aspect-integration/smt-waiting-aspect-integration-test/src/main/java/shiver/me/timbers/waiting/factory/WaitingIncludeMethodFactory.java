package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanIgnoreAllWaitingIncludeMethod;
import shiver.me.timbers.waiting.execution.CanIgnoreWaitingIncludeMethod;

public class WaitingIncludeMethodFactory extends WaitingIncludeFactory {

    public WaitingIncludeMethodFactory() {
        this(new CanIgnoreWaitingIncludeMethod(), new CanIgnoreAllWaitingIncludeMethod());
    }

    public WaitingIncludeMethodFactory(
        CanIgnoreWaitingIncludeMethod canIgnoreWaitingIncludeMethod,
        CanIgnoreAllWaitingIncludeMethod canIgnoreAllWaitingIncludeMethod
    ) {
        super(canIgnoreWaitingIncludeMethod, canIgnoreAllWaitingIncludeMethod);
    }
}
