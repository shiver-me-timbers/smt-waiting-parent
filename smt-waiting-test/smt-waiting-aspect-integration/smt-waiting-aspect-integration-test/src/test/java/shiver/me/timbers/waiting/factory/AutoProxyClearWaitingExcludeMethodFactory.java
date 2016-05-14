package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.ClearAndAddExcludeWaitingForMethod;

@Component
public class AutoProxyClearWaitingExcludeMethodFactory extends ClearWaitingExcludeMethodFactory {

    @Autowired
    public AutoProxyClearWaitingExcludeMethodFactory(
        ClearAndAddExcludeWaitingForMethod clearAndAddExcludeWaitingForMethod
    ) {
        super(clearAndAddExcludeWaitingForMethod);
    }
}
