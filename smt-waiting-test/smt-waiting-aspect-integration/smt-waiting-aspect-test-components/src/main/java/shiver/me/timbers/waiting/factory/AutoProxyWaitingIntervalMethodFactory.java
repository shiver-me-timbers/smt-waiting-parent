package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanChangeWaitingIntervalMethod;

@Component
public class AutoProxyWaitingIntervalMethodFactory extends WaitingIntervalMethodFactory {

    @Autowired
    public AutoProxyWaitingIntervalMethodFactory(CanChangeWaitingIntervalMethod canChangeWaitingIntervalMethod) {
        super(canChangeWaitingIntervalMethod);
    }
}
