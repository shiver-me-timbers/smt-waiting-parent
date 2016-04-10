package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingForTrueClassFactory;

public class ITAutoProxySpringAspectWaiterWaitForTruePropertyClass extends AbstractITSpringAspectWaiterWaitForTruePropertyClass {

    @Autowired
    private AutoProxyWaitingDefaultsClassFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingForTrueClassFactory waitingForTrueFactory;

    @Override
    public AutoProxyWaitingDefaultsClassFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingForTrueClassFactory waitForTrueFactory() {
        return waitingForTrueFactory;
    }
}
