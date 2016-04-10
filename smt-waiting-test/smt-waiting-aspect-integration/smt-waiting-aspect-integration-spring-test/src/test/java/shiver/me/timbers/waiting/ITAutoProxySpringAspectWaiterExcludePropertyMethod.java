package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingExcludeMethodFactory;

public class ITAutoProxySpringAspectWaiterExcludePropertyMethod extends AbstractITSpringAspectWaiterExcludePropertyMethod {

    @Autowired
    private AutoProxyWaitingDefaultsMethodFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingExcludeMethodFactory excludesFactory;

    @Override
    public AutoProxyWaitingDefaultsMethodFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingExcludeMethodFactory excludesFactory() {
        return excludesFactory;
    }
}
