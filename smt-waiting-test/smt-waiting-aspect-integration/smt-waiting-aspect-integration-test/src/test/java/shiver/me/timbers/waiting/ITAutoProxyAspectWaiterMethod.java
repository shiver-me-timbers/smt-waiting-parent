package shiver.me.timbers.waiting;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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

    @Override
    protected AutoProxyWaitingIntervalMethodFactory intervalFactory() {
        return intervalFactory;
    }

    @Override
    protected AutoProxyWaitingTimeoutMethodFactory timeoutFactory() {
        return timeoutFactory;
    }

    @Override
    protected AutoProxyWaitingForMethodFactory waitForFactory() {
        return waitForFactory;
    }

    @Override
    protected AutoProxyWaitingForNotNullMethodFactory waitForNotNullFactory() {
        return waitForNotNullFactory;
    }

    @Override
    protected AutoProxyWaitingForTrueMethodFactory waitForTrueFactory() {
        return waitForTrueFactory;
    }

    @Override
    protected AutoProxyWaitingIncludeMethodFactory includesFactory() {
        return includesFactory;
    }

    @Override
    protected AutoProxyWaitingIncludesWithExcludesMethodFactory includesWithExcludesFactory() {
        return includesWithExcludesFactory;
    }

    @Override
    protected AutoProxyWaitingExcludeMethodFactory excludesFactory() {
        return excludesFactory;
    }

    @Override
    protected AutoProxyWaitingExcludesWithIncludesMethodFactory excludesWithIncludesFactory() {
        return excludesWithIncludesFactory;
    }
}
