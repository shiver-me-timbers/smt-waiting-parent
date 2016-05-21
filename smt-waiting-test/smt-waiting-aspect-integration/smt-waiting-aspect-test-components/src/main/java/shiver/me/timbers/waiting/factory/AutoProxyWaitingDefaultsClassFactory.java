package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.WaitingDefaultsClass;

@Component
public class AutoProxyWaitingDefaultsClassFactory extends WaitingDefaultsClassFactory {

    @Autowired
    public AutoProxyWaitingDefaultsClassFactory(WaitingDefaultsClass waitingDefaults) {
        super(waitingDefaults);
    }
}
