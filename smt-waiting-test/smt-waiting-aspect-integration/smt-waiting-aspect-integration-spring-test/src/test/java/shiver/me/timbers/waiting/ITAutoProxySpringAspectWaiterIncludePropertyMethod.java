package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingIncludeMethodFactory;

public class ITAutoProxySpringAspectWaiterIncludePropertyMethod extends AbstractITSpringAspectWaiterIncludePropertyMethod {

    @Autowired
    private AutoProxyWaitingDefaultsMethodFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingIncludeMethodFactory excludesFactory;

    @Override
    public AutoProxyWaitingDefaultsMethodFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingIncludeMethodFactory includesFactory() {
        return excludesFactory;
    }
}
