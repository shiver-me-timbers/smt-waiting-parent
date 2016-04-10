package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingExcludeMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingExcludesWithIncludesMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingForMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingForNotNullMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingForTrueMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingIncludeMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingIncludesWithExcludesMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingIntervalMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingTimeoutMethodFactory;

public abstract class AbstractITAspectWaiterMethod extends AbstractITAspectWaiter {

    @Override
    public WaitingIntervalMethodFactory intervalFactory() {
        return new WaitingIntervalMethodFactory();
    }

    @Override
    public WaitingTimeoutMethodFactory timeoutFactory() {
        return new WaitingTimeoutMethodFactory();
    }

    @Override
    public WaitingForMethodFactory waitForFactory() {
        return new WaitingForMethodFactory();
    }

    @Override
    public WaitingForNotNullMethodFactory waitForNotNullFactory() {
        return new WaitingForNotNullMethodFactory();
    }

    @Override
    public WaitingForTrueMethodFactory waitForTrueFactory() {
        return new WaitingForTrueMethodFactory();
    }

    @Override
    public WaitingIncludeMethodFactory includesFactory() {
        return new WaitingIncludeMethodFactory();
    }

    @Override
    public WaitingIncludesWithExcludesMethodFactory includesWithExcludesFactory() {
        return new WaitingIncludesWithExcludesMethodFactory();
    }

    @Override
    public WaitingExcludeMethodFactory excludesFactory() {
        return new WaitingExcludeMethodFactory();
    }

    @Override
    public WaitingExcludesWithIncludesMethodFactory excludesWithIncludesFactory() {
        return new WaitingExcludesWithIncludesMethodFactory();
    }
}
