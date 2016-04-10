package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanAddExtraWaitingIncludeClass;
import shiver.me.timbers.waiting.execution.CanIgnoreAllWaitingIncludeClass;
import shiver.me.timbers.waiting.execution.CanIgnoreWaitingIncludeClass;

@Component
public class AutoProxyWaitingIncludeClassFactory extends WaitingIncludeClassFactory {

    @Autowired
    public AutoProxyWaitingIncludeClassFactory(
        CanIgnoreWaitingIncludeClass canIgnoreWaitingIncludeClass,
        CanIgnoreAllWaitingIncludeClass canIgnoreAllWaitingIncludeClass,
        CanAddExtraWaitingIncludeClass canAddExtraWaitingIncludeClass
    ) {
        super(canIgnoreWaitingIncludeClass, canIgnoreAllWaitingIncludeClass, canAddExtraWaitingIncludeClass);
    }
}
