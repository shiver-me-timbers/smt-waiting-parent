package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingExcludeClassFactory;

public class ITAutoProxySpringAspectWaiterExcludePropertyClass extends AbstractITSpringAspectWaiterExcludePropertyClass {

    @Autowired
    private AutoProxyWaitingDefaultsClassFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingExcludeClassFactory excludesFactory;

    @Override
    public AutoProxyWaitingDefaultsClassFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingExcludeClassFactory excludesFactory() {
        return excludesFactory;
    }
}
