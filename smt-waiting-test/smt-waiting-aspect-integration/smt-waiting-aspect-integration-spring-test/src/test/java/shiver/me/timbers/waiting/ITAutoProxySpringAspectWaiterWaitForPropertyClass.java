package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyClearWaitingForClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingForClassFactory;

public class ITAutoProxySpringAspectWaiterWaitForPropertyClass extends AbstractITSpringAspectWaiterWaitForPropertyClass {

    @Autowired
    private AutoProxyWaitingDefaultsClassFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingForClassFactory waitingForFactory;

    @Autowired
    private AutoProxyClearWaitingForClassFactory clearWaitingForFactory;

    @Override
    public AutoProxyWaitingDefaultsClassFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingForClassFactory waitForFactory() {
        return waitingForFactory;
    }

    @Override
    public AutoProxyClearWaitingForClassFactory clearWaitForFactory() {
        return clearWaitingForFactory;
    }
}
