package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.WaitingDefaultsMethod;
import shiver.me.timbers.waiting.execution.WaitingWithDefaultsMethod;

@Component
public class AutoProxyWaitingWithDefaultsMethodFactory extends WaitingWithDefaultsMethodFactory {

    @Autowired
    public AutoProxyWaitingWithDefaultsMethodFactory(
        WaitingDefaultsMethod waitingWithDefaults,
        WaitingWithDefaultsMethod waitingWithoutDefaults
    ) {
        super(waitingWithDefaults, waitingWithoutDefaults);
    }
}
