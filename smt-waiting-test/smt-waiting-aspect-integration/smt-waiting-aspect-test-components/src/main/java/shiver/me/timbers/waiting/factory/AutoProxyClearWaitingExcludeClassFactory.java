package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.ClearAndAddExcludeWaitingForClass;

@Component
public class AutoProxyClearWaitingExcludeClassFactory extends ClearWaitingExcludeClassFactory {

    @Autowired
    public AutoProxyClearWaitingExcludeClassFactory(
        ClearAndAddExcludeWaitingForClass clearAndAddExcludeWaitingForClass
    ) {
        super(clearAndAddExcludeWaitingForClass);
    }
}
