package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingIntervalMethodFactory;

public class ITAutoProxySpringAspectWaiterIntervalPropertyMethod extends AbstractITSpringAspectWaiterIntervalPropertyMethod {

    @Autowired
    private AutoProxyWaitingDefaultsMethodFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingIntervalMethodFactory intervalFactory;

    @Override
    public AutoProxyWaitingDefaultsMethodFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingIntervalMethodFactory intervalFactory() {
        return intervalFactory;
    }
}
