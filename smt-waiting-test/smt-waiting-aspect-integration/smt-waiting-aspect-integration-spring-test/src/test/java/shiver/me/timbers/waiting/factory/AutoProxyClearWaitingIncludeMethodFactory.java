package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.ClearAndAddIncludeWaitingForMethod;

@Component
public class AutoProxyClearWaitingIncludeMethodFactory extends ClearWaitingIncludeMethodFactory {

    @Autowired
    public AutoProxyClearWaitingIncludeMethodFactory(ClearAndAddIncludeWaitingForMethod clearAndAddIncludeWaitingForMethod) {
        super(clearAndAddIncludeWaitingForMethod);
    }
}
