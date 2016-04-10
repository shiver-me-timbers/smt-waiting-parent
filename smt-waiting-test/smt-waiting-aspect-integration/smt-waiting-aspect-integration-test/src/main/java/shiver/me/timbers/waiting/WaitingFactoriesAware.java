package shiver.me.timbers.waiting;

public interface WaitingFactoriesAware extends
    WaitingIntervalFactoryAware,
    WaitingTimeoutFactoryAware,
    WaitingForFactoryAware,
    WaitingForNotNullFactoryAware,
    WaitingForTrueFactoryAware,
    WaitingIncludeFactoryAware,
    WaitingIncludesWithExcludesFactoryAware,
    WaitingExcludeFactoryAware,
    WaitingExcludesWithIncludesFactoryAware {

}
