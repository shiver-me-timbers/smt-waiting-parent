package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingForMethodFactory;

public class ITAutoProxySpringAspectWaiterWaitForPropertyMethod extends AbstractITSpringAspectWaiterWaitForPropertyMethod {

    @Autowired
    private AutoProxyWaitingDefaultsMethodFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingForMethodFactory waitingForFactory;

    @Override
    public AutoProxyWaitingDefaultsMethodFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingForMethodFactory waitForFactory() {
        return waitingForFactory;
    }
}
