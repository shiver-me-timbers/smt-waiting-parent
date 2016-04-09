package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoProxyWaitingIntervalClassFactory extends WaitingIntervalClassFactory {

    @Autowired
    public AutoProxyWaitingIntervalClassFactory(CanChangeWaitingIntervalClass canChangeWaitingIntervalClass) {
        super(canChangeWaitingIntervalClass);
    }
}
