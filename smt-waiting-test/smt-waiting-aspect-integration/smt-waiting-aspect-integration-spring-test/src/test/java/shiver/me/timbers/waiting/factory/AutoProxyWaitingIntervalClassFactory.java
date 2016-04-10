package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanChangeWaitingIntervalClass;

@Component
public class AutoProxyWaitingIntervalClassFactory extends WaitingIntervalClassFactory {

    @Autowired
    public AutoProxyWaitingIntervalClassFactory(CanChangeWaitingIntervalClass canChangeWaitingIntervalClass) {
        super(canChangeWaitingIntervalClass);
    }
}
