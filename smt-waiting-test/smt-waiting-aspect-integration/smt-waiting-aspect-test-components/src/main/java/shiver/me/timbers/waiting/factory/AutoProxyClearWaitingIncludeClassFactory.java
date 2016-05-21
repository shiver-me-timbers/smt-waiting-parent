package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.ClearAndAddIncludeWaitingForClass;

@Component
public class AutoProxyClearWaitingIncludeClassFactory extends ClearWaitingIncludeClassFactory {

    @Autowired
    public AutoProxyClearWaitingIncludeClassFactory(ClearAndAddIncludeWaitingForClass clearAndAddIncludeWaitingForClass) {
        super(clearAndAddIncludeWaitingForClass);
    }
}
