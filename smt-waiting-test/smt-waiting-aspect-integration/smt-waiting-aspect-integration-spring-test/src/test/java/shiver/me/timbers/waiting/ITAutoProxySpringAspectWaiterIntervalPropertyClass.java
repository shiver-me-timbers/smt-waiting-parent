package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingIntervalClassFactory;

public class ITAutoProxySpringAspectWaiterIntervalPropertyClass extends AbstractITSpringAspectWaiterIntervalPropertyClass {

    @Autowired
    private AutoProxyWaitingDefaultsClassFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingIntervalClassFactory intervalFactory;

    @Override
    public AutoProxyWaitingDefaultsClassFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingIntervalClassFactory intervalFactory() {
        return intervalFactory;
    }
}
