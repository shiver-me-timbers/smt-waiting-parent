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
    public AutoProxyWaitingIntervalClassFactory intervalFactory() {
        return intervalFactory;
    }

    @Override
    public AutoProxyWaitingTimeoutClassFactory timeoutFactory() {
        return timeoutFactory;
    }

    @Override
    public AutoProxyWaitingForClassFactory waitForFactory() {
        return waitForFactory;
    }

    @Override
    public AutoProxyWaitingForNotNullClassFactory waitForNotNullFactory() {
        return waitForNotNullFactory;
    }

    @Override
    public AutoProxyWaitingForTrueClassFactory waitForTrueFactory() {
        return waitForTrueFactory;
    }

    @Override
    public AutoProxyWaitingIncludeClassFactory includesFactory() {
        return includesFactory;
    }

    @Override
    public AutoProxyWaitingIncludesWithExcludesClassFactory includesWithExcludesFactory() {
        return includesWithExcludesFactory;
    }

    @Override
    public AutoProxyWaitingExcludeClassFactory excludesFactory() {
        return excludesFactory;
    }

    @Override
    public AutoProxyWaitingExcludesWithIncludesClassFactory excludesWithIncludesFactory() {
        return excludesWithIncludesFactory;
    }
}
