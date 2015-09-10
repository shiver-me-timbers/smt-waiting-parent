package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

/**
 * @author Karl Bennett
 */
interface OptionsService {

    Options withDefaults(boolean useDefaults);

    Options withTimeOut(Long duration, TimeUnit unit);

    Options willWaitForTrue(boolean shouldWait);

    Options willWaitForNotNull(boolean shouldWait);

    Options withInterval(long duration, TimeUnit unit);

    Options waitFor(ResultValidator resultValidator);

    Choice choose();
}
