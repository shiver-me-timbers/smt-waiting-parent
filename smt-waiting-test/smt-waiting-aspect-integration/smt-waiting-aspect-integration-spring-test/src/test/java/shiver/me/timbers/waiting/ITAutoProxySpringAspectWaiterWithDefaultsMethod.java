package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingWithDefaultsMethodFactory;

public class ITAutoProxySpringAspectWaiterWithDefaultsMethod extends AbstractITSpringAspectWaiterWithDefaultsMethod {

    @Autowired
    private AutoProxyWaitingWithDefaultsMethodFactory waitingWithDefaultsFactory;

    @Override
    public AutoProxyWaitingWithDefaultsMethodFactory withDefaultsFactory() {
        return waitingWithDefaultsFactory;
    }
}
