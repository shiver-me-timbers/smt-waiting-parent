package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

/**
 * @author Karl Bennett
 */
interface OptionsService {

    Options withDefaults();

    Options withTimeOut(Long duration, TimeUnit unit);

    Options waitFor(ResultValidator resultValidator);

    Options willWaitForTrue();

    Options willNotWaitForTrue();

    Options willWaitForNotNull();

    Options willNotWaitForNotNull();

    Options withInterval(long duration, TimeUnit unit);
}
