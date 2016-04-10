package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingWithDefaultsClassFactory;

public class ITAutoProxySpringAspectWaiterWithDefaultsClass extends AbstractITSpringAspectWaiterWithDefaultsClass {

    @Autowired
    private AutoProxyWaitingWithDefaultsClassFactory waitingWithDefaultsFactory;

    @Override
    public AutoProxyWaitingWithDefaultsClassFactory withDefaultsFactory() {
        return waitingWithDefaultsFactory;
    }
}
