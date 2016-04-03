package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_THROWABLES;

@Component
public class AutoProxyWaitingExcludeFactory extends WaitingExcludeFactory {

    @Autowired
    public AutoProxyWaitingExcludeFactory(
        CannotIgnoreWaitingExcludeClassComponent cannotIgnoreWaitingExcludeClassComponent,
        CanIgnoreWaitingExcludeClassComponent canIgnoreWaitingExcludeClassComponent
    ) {
        add(
            cannotIgnoreWaitingExcludeClassComponent,
            500L, MILLISECONDS, SOME_THROWABLES[0], SOME_OTHER_THROWABLES[0], SOME_THROWABLES[1]
        );
        add(
            canIgnoreWaitingExcludeClassComponent,
            500L, MILLISECONDS, SOME_OTHER_THROWABLES
        );
    }
}
