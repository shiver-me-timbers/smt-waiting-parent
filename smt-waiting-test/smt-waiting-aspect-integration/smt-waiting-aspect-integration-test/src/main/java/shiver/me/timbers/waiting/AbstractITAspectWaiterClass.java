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
    protected WaitingIntervalClassFactory intervalFactory() {
        return new WaitingIntervalClassFactory();
    }

    @Override
    protected WaitingTimeoutClassFactory timeoutFactory() {
        return new WaitingTimeoutClassFactory();
    }

    @Override
    protected WaitingForClassFactory waitForFactory() {
        return new WaitingForClassFactory();
    }

    @Override
    protected WaitingForNotNullClassFactory waitForNotNullFactory() {
        return new WaitingForNotNullClassFactory();
    }

    @Override
    protected WaitingForTrueClassFactory waitForTrueFactory() {
        return new WaitingForTrueClassFactory();
    }

    @Override
    protected WaitingIncludeClassFactory includesFactory() {
        return new WaitingIncludeClassFactory();
    }

    @Override
    protected WaitingIncludesWithExcludesClassFactory includesWithExcludesFactory() {
        return new WaitingIncludesWithExcludesClassFactory();
    }

    @Override
    protected WaitingExcludeClassFactory excludesFactory() {
        return new WaitingExcludeClassFactory();
    }

    @Override
    protected WaitingExcludesWithIncludesClassFactory excludesWithIncludesFactory() {
        return new WaitingExcludesWithIncludesClassFactory();
    }
}
