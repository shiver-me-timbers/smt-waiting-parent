package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.ClearAndWaitUntilSuccessWaitingForClass;

@Component
public class AutoProxyClearWaitingForClassFactory extends ClearWaitingForClassFactory {

    @Autowired
    public AutoProxyClearWaitingForClassFactory(
        ClearAndWaitUntilSuccessWaitingForClass clearAndWaitUntilSuccessWaitingForClass
    ) {
        super(clearAndWaitUntilSuccessWaitingForClass);
    }
}
