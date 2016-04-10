package shiver.me.timbers.waiting;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingExcludeClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingExcludesWithIncludesClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingForClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingForNotNullClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingForTrueClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingIncludeClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingIncludesWithExcludesClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingIntervalClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingTimeoutClassFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WaiterConfiguration.class)
public class ITAutoProxyAspectWaiterClass extends AbstractITAspectWaiterClass {

    @Autowired
    private AutoProxyWaitingIntervalClassFactory intervalFactory;

    @Autowired
    private AutoProxyWaitingTimeoutClassFactory timeoutFactory;

    @Autowired
    private AutoProxyWaitingForClassFactory waitForFactory;

    @Autowired
    private AutoProxyWaitingForNotNullClassFactory waitForNotNullFactory;

    @Autowired
    private AutoProxyWaitingForTrueClassFactory waitForTrueFactory;

    @Autowired
    private AutoProxyWaitingIncludeClassFactory includesFactory;

    @Autowired
    private AutoProxyWaitingIncludesWithExcludesClassFactory includesWithExcludesFactory;

    @Autowired
    private AutoProxyWaitingExcludeClassFactory excludesFactory;

    @Autowired
    private AutoProxyWaitingExcludesWithIncludesClassFactory excludesWithIncludesFactory;

    @Override
    protected AutoProxyWaitingIntervalClassFactory intervalFactory() {
        return intervalFactory;
    }

    @Override
    protected AutoProxyWaitingTimeoutClassFactory timeoutFactory() {
        return timeoutFactory;
    }

    @Override
    protected AutoProxyWaitingForClassFactory waitForFactory() {
        return waitForFactory;
    }

    @Override
    protected AutoProxyWaitingForNotNullClassFactory waitForNotNullFactory() {
        return waitForNotNullFactory;
    }

    @Override
    protected AutoProxyWaitingForTrueClassFactory waitForTrueFactory() {
        return waitForTrueFactory;
    }

    @Override
    protected AutoProxyWaitingIncludeClassFactory includesFactory() {
        return includesFactory;
    }

    @Override
    protected AutoProxyWaitingIncludesWithExcludesClassFactory includesWithExcludesFactory() {
        return includesWithExcludesFactory;
    }

    @Override
    protected AutoProxyWaitingExcludeClassFactory excludesFactory() {
        return excludesFactory;
    }

    @Override
    protected AutoProxyWaitingExcludesWithIncludesClassFactory excludesWithIncludesFactory() {
        return excludesWithIncludesFactory;
    }
}
