package shiver.me.timbers.waiting;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WaiterConfiguration.class)
public class ITAutoProxyWaiterClass extends AbstractITAspectWaiter {

    @Autowired
    private AutoProxyWaitingIntervalFactory intervalFactory;

    @Autowired
    private AutoProxyWaitingTimeoutFactory timeoutFactory;

    @Autowired
    private AutoProxyWaitingForFactory waitForFactory;

    @Autowired
    private AutoProxyWaitingForNotNullFactory waitForNotNullFactory;

    @Autowired
    private AutoProxyWaitingForTrueFactory waitForTrueFactory;

    @Autowired
    private AutoProxyWaitingIncludeFactory includesFactory;

    @Autowired
    private AutoProxyWaitingIncludesWithExcludesFactory includesWithExcludesFactory;

    @Autowired
    private AutoProxyWaitingExcludeFactory excludesFactory;

    @Autowired
    private AutoProxyWaitingExcludesWithIncludesFactory excludesWithIncludesFactory;

    @Override
    protected AutoProxyWaitingIntervalFactory intervalFactory() {
        return intervalFactory;
    }

    @Override
    protected AutoProxyWaitingTimeoutFactory timeoutFactory() {
        return timeoutFactory;
    }

    @Override
    protected AutoProxyWaitingForFactory waitForFactory() {
        return waitForFactory;
    }

    @Override
    protected AutoProxyWaitingForNotNullFactory waitForNotNullFactory() {
        return waitForNotNullFactory;
    }

    @Override
    protected AutoProxyWaitingForTrueFactory waitForTrueFactory() {
        return waitForTrueFactory;
    }

    @Override
    protected AutoProxyWaitingIncludeFactory includesFactory() {
        return includesFactory;
    }

    @Override
    protected AutoProxyWaitingIncludesWithExcludesFactory includesWithExcludesFactory() {
        return includesWithExcludesFactory;
    }

    @Override
    protected AutoProxyWaitingExcludeFactory excludesFactory() {
        return excludesFactory;
    }

    @Override
    protected AutoProxyWaitingExcludesWithIncludesFactory excludesWithIncludesFactory() {
        return excludesWithIncludesFactory;
    }
}
