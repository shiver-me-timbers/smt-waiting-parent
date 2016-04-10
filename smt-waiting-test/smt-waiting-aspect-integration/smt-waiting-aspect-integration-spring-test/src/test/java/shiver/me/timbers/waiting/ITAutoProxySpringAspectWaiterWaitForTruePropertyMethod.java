package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingForTrueMethodFactory;

public class ITAutoProxySpringAspectWaiterWaitForTruePropertyMethod extends AbstractITSpringAspectWaiterWaitForTruePropertyMethod {

    @Autowired
    private AutoProxyWaitingDefaultsMethodFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingForTrueMethodFactory waitingForTrueFactory;

    @Override
    public AutoProxyWaitingDefaultsMethodFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingForTrueMethodFactory waitForTrueFactory() {
        return waitingForTrueFactory;
    }
}
