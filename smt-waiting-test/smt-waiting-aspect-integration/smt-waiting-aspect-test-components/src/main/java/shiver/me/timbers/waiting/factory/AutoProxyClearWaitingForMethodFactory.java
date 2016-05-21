package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.ClearAndWaitUntilSuccessWaitingForMethod;

@Component
public class AutoProxyClearWaitingForMethodFactory extends ClearWaitingForMethodFactory {

    @Autowired
    public AutoProxyClearWaitingForMethodFactory(
        ClearAndWaitUntilSuccessWaitingForMethod clearAndWaitUntilSuccessWaitingForMethod
    ) {
        super(clearAndWaitUntilSuccessWaitingForMethod);
    }
}
