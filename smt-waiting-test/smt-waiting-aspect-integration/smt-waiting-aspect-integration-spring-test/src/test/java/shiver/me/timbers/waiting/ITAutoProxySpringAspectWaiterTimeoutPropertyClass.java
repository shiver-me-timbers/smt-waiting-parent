package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingTimeoutClassFactory;

public class ITAutoProxySpringAspectWaiterTimeoutPropertyClass extends AbstractITSpringAspectWaiterTimeoutPropertyClass {

    @Autowired
    private AutoProxyWaitingDefaultsClassFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingTimeoutClassFactory timeoutFactory;

    @Override
    public AutoProxyWaitingDefaultsClassFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingTimeoutClassFactory timeoutFactory() {
        return timeoutFactory;
    }
}
