package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingForNotNullMethodFactory;

public class ITAutoProxySpringAspectWaiterWaitForNotNullPropertyMethod extends AbstractITSpringAspectWaiterWaitForNotNullPropertyMethod {

    @Autowired
    private AutoProxyWaitingDefaultsMethodFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingForNotNullMethodFactory notNullFactory;

    @Override
    public AutoProxyWaitingDefaultsMethodFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingForNotNullMethodFactory waitForNotNullFactory() {
        return notNullFactory;
    }
}
