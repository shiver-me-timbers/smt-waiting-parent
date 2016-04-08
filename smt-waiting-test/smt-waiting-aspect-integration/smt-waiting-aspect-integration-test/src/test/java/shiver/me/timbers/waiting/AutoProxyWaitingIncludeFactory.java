package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_THROWABLES;

@Component
public class AutoProxyWaitingIncludeFactory extends WaitingIncludeFactory {

    @Autowired
    public AutoProxyWaitingIncludeFactory(
        CanIgnoreWaitingIncludeClass canIgnoreWaitingIncludeClass,
        CanIgnoreAllWaitingIncludeClass canIgnoreAllWaitingIncludeClass
    ) {
        add(canIgnoreWaitingIncludeClass, 500L, MILLISECONDS, SOME_THROWABLES);
        add(canIgnoreAllWaitingIncludeClass, 500L, MILLISECONDS);
    }
}
