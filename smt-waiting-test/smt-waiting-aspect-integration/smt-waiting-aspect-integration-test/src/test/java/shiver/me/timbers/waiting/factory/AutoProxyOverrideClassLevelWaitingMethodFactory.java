package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanOverrideClassLevelWaitWithMethodLevelWait;

@Component
public class AutoProxyOverrideClassLevelWaitingMethodFactory extends OverrideClassLevelWaitingMethodFactory {

    @Autowired
    public AutoProxyOverrideClassLevelWaitingMethodFactory(
        CanOverrideClassLevelWaitWithMethodLevelWait canOverrideClassLevelWaitWithMethodLevelWait
    ) {
        super(canOverrideClassLevelWaitWithMethodLevelWait);
    }
}
