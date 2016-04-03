package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_THROWABLES;

@Component
public class AutoProxyWaitingExcludesWithIncludesFactory extends WaitingExcludesWithIncludesFactory {

    @Autowired
    public AutoProxyWaitingExcludesWithIncludesFactory(
        CannotIgnoreExcludeIncludeWaitingExcludeClassComponent cannotIgnoreExcludeIncludeWaitingExcludeClassComponent,
        ExcludePrecedenceWaitingExcludeClassComponent excludePrecedenceWaitingExcludeClassComponent
    ) {
        add(
            cannotIgnoreExcludeIncludeWaitingExcludeClassComponent,
            500L, MILLISECONDS, asList(SOME_THROWABLES), asList(SOME_OTHER_THROWABLES)
        );
        add(
            excludePrecedenceWaitingExcludeClassComponent,
            500L, MILLISECONDS, singletonList(SOME_THROWABLES[0]), singletonList(SOME_THROWABLES[0])
        );
    }
}
