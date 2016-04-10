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
    protected WaitingIntervalMethodFactory intervalFactory() {
        return new WaitingIntervalMethodFactory();
    }

    @Override
    protected WaitingTimeoutMethodFactory timeoutFactory() {
        return new WaitingTimeoutMethodFactory();
    }

    @Override
    protected WaitingForMethodFactory waitForFactory() {
        return new WaitingForMethodFactory();
    }

    @Override
    protected WaitingForNotNullMethodFactory waitForNotNullFactory() {
        return new WaitingForNotNullMethodFactory();
    }

    @Override
    protected WaitingForTrueMethodFactory waitForTrueFactory() {
        return new WaitingForTrueMethodFactory();
    }

    @Override
    protected WaitingIncludeMethodFactory includesFactory() {
        return new WaitingIncludeMethodFactory();
    }

    @Override
    protected WaitingIncludesWithExcludesMethodFactory includesWithExcludesFactory() {
        return new WaitingIncludesWithExcludesMethodFactory();
    }

    @Override
    protected WaitingExcludeMethodFactory excludesFactory() {
        return new WaitingExcludeMethodFactory();
    }

    @Override
    protected WaitingExcludesWithIncludesMethodFactory excludesWithIncludesFactory() {
        return new WaitingExcludesWithIncludesMethodFactory();
    }
}
