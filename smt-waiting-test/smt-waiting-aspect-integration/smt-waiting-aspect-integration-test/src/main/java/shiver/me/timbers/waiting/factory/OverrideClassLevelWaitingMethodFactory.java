package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanOverrideClassLevelWaitWithMethodLevelWait;
import shiver.me.timbers.waiting.execution.WaitingClassLevelOverride;

public class OverrideClassLevelWaitingMethodFactory {

    private final CanOverrideClassLevelWaitWithMethodLevelWait canOverrideClassLevelWaitWithMethodLevelWait;

    public OverrideClassLevelWaitingMethodFactory() {
        this(new CanOverrideClassLevelWaitWithMethodLevelWait());
    }

    public OverrideClassLevelWaitingMethodFactory(
        CanOverrideClassLevelWaitWithMethodLevelWait canOverrideClassLevelWaitWithMethodLevelWait
    ) {
        this.canOverrideClassLevelWaitWithMethodLevelWait = canOverrideClassLevelWaitWithMethodLevelWait;
    }

    public WaitingClassLevelOverride create() {
        return canOverrideClassLevelWaitWithMethodLevelWait;
    }
}
