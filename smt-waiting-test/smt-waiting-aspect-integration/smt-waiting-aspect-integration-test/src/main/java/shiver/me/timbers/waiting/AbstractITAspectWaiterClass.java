package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingExcludeClassFactory;
import shiver.me.timbers.waiting.factory.WaitingExcludesWithIncludesClassFactory;
import shiver.me.timbers.waiting.factory.WaitingForClassFactory;
import shiver.me.timbers.waiting.factory.WaitingForNotNullClassFactory;
import shiver.me.timbers.waiting.factory.WaitingForTrueClassFactory;
import shiver.me.timbers.waiting.factory.WaitingIncludeClassFactory;
import shiver.me.timbers.waiting.factory.WaitingIncludesWithExcludesClassFactory;
import shiver.me.timbers.waiting.factory.WaitingIntervalClassFactory;
import shiver.me.timbers.waiting.factory.WaitingTimeoutClassFactory;

public abstract class AbstractITAspectWaiterClass extends AbstractITAspectWaiter {

    @Override
    public WaitingIntervalClassFactory intervalFactory() {
        return new WaitingIntervalClassFactory();
    }

    @Override
    public WaitingTimeoutClassFactory timeoutFactory() {
        return new WaitingTimeoutClassFactory();
    }

    @Override
    public WaitingForClassFactory waitForFactory() {
        return new WaitingForClassFactory();
    }

    @Override
    public WaitingForNotNullClassFactory waitForNotNullFactory() {
        return new WaitingForNotNullClassFactory();
    }

    @Override
    public WaitingForTrueClassFactory waitForTrueFactory() {
        return new WaitingForTrueClassFactory();
    }

    @Override
    public WaitingIncludeClassFactory includesFactory() {
        return new WaitingIncludeClassFactory();
    }

    @Override
    public WaitingIncludesWithExcludesClassFactory includesWithExcludesFactory() {
        return new WaitingIncludesWithExcludesClassFactory();
    }

    @Override
    public WaitingExcludeClassFactory excludesFactory() {
        return new WaitingExcludeClassFactory();
    }

    @Override
    public WaitingExcludesWithIncludesClassFactory excludesWithIncludesFactory() {
        return new WaitingExcludesWithIncludesClassFactory();
    }
}
