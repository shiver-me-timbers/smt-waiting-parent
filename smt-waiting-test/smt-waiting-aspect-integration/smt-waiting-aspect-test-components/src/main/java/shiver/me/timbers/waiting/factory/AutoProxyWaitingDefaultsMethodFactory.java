package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.WaitingDefaultsMethod;

@Component
public class AutoProxyWaitingDefaultsMethodFactory extends WaitingDefaultsMethodFactory {

    @Autowired
    public AutoProxyWaitingDefaultsMethodFactory(WaitingDefaultsMethod waitingDefaults) {
        super(waitingDefaults);
    }
}
