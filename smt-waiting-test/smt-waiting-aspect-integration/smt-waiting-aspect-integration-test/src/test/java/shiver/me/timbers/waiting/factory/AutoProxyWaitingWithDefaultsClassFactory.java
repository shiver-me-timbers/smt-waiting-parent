package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.WaitingDefaultsClass;
import shiver.me.timbers.waiting.execution.WaitingWithDefaultsClass;

@Component
public class AutoProxyWaitingWithDefaultsClassFactory extends WaitingWithDefaultsClassFactory {

    @Autowired
    public AutoProxyWaitingWithDefaultsClassFactory(
        WaitingDefaultsClass waitingWithDefaults,
        WaitingWithDefaultsClass waitingWithoutDefaults
    ) {
        super(waitingWithDefaults, waitingWithoutDefaults);
    }
}
