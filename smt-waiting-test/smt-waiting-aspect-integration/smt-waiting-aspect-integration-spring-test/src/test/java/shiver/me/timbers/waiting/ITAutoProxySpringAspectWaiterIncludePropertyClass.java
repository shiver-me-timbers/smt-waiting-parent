package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingIncludeClassFactory;

public class ITAutoProxySpringAspectWaiterIncludePropertyClass extends AbstractITSpringAspectWaiterIncludePropertyClass {

    @Autowired
    private AutoProxyWaitingDefaultsClassFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingIncludeClassFactory excludesFactory;

    @Override
    public AutoProxyWaitingDefaultsClassFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingIncludeClassFactory includesFactory() {
        return excludesFactory;
    }
}
