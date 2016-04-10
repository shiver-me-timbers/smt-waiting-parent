package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanIgnoreWaitingExcludeMethod;
import shiver.me.timbers.waiting.execution.CannotIgnoreWaitingExcludeMethod;

public class WaitingExcludeMethodFactory extends WaitingExcludeFactory {

    public WaitingExcludeMethodFactory() {
        this(new CannotIgnoreWaitingExcludeMethod(), new CanIgnoreWaitingExcludeMethod());
    }

    public WaitingExcludeMethodFactory(
        CannotIgnoreWaitingExcludeMethod cannotIgnoreWaitingExcludeMethod,
        CanIgnoreWaitingExcludeMethod canIgnoreWaitingExcludeMethod
    ) {
        super(cannotIgnoreWaitingExcludeMethod, canIgnoreWaitingExcludeMethod);
    }
}
