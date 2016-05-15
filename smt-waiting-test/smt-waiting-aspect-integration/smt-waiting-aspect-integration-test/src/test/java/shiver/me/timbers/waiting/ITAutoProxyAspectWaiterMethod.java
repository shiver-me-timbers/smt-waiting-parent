package shiver.me.timbers.waiting;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shiver.me.timbers.waiting.factory.AutoProxyOverrideClassLevelWaitingMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingExcludeMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingExcludesWithIncludesMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingForMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingForNotNullMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingForTrueMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingIncludeMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingIncludesWithExcludesMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingIntervalMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingTimeoutMethodFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WaiterConfiguration.class)
public class ITAutoProxyAspectWaiterMethod extends AbstractITAspectWaiterMethod {

    @Autowired
    private AutoProxyWaitingIntervalMethodFactory intervalFactory;

    @Autowired
    private AutoProxyWaitingTimeoutMethodFactory timeoutFactory;

    @Autowired
    private AutoProxyWaitingForMethodFactory waitForFactory;

    @Autowired
    private AutoProxyWaitingForNotNullMethodFactory waitForNotNullFactory;

    @Autowired
    private AutoProxyWaitingForTrueMethodFactory waitForTrueFactory;

    @Autowired
    private AutoProxyWaitingIncludeMethodFactory includesFactory;

    @Autowired
    private AutoProxyWaitingIncludesWithExcludesMethodFactory includesWithExcludesFactory;

    @Autowired
    private AutoProxyWaitingExcludeMethodFactory excludesFactory;

    @Autowired
    private AutoProxyWaitingExcludesWithIncludesMethodFactory excludesWithIncludesFactory;

    @Autowired
    private AutoProxyOverrideClassLevelWaitingMethodFactory overrideClassLevelWaitingMethodFactory;

    @Override
    public AutoProxyWaitingIntervalMethodFactory intervalFactory() {
        return intervalFactory;
    }

    @Override
    public AutoProxyWaitingTimeoutMethodFactory timeoutFactory() {
        return timeoutFactory;
    }

    @Override
    public AutoProxyWaitingForMethodFactory waitForFactory() {
        return waitForFactory;
    }

    @Override
    public AutoProxyWaitingForNotNullMethodFactory waitForNotNullFactory() {
        return waitForNotNullFactory;
    }

    @Override
    public AutoProxyWaitingForTrueMethodFactory waitForTrueFactory() {
        return waitForTrueFactory;
    }

    @Override
    public AutoProxyWaitingIncludeMethodFactory includesFactory() {
        return includesFactory;
    }

    @Override
    public AutoProxyWaitingIncludesWithExcludesMethodFactory includesWithExcludesFactory() {
        return includesWithExcludesFactory;
    }

    @Override
    public AutoProxyWaitingExcludeMethodFactory excludesFactory() {
        return excludesFactory;
    }

    @Override
    public AutoProxyWaitingExcludesWithIncludesMethodFactory excludesWithIncludesFactory() {
        return excludesWithIncludesFactory;
    }

    @Override
    public AutoProxyOverrideClassLevelWaitingMethodFactory overrideClassLevelWaitingMethodFactory() {
        return overrideClassLevelWaitingMethodFactory;
    }
}
