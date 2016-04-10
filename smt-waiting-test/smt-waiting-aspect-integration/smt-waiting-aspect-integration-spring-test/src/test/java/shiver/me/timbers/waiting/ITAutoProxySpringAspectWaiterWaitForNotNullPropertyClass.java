package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingForNotNullClassFactory;

public class ITAutoProxySpringAspectWaiterWaitForNotNullPropertyClass extends AbstractITSpringAspectWaiterWaitForNotNullPropertyClass {

    @Autowired
    private AutoProxyWaitingDefaultsClassFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingForNotNullClassFactory notNullFactory;

    @Override
    public AutoProxyWaitingDefaultsClassFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingForNotNullClassFactory waitForNotNullFactory() {
        return notNullFactory;
    }
}
