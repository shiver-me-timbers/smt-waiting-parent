package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingTimeoutMethodFactory;

public class ITAutoProxySpringAspectWaiterTimeoutPropertyMethod extends AbstractITSpringAspectWaiterTimeoutPropertyMethod {

    @Autowired
    private AutoProxyWaitingDefaultsMethodFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingTimeoutMethodFactory timeoutFactory;

    @Override
    public AutoProxyWaitingDefaultsMethodFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingTimeoutMethodFactory timeoutFactory() {
        return timeoutFactory;
    }
}
