package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Karl Bennett
 */
interface Choices {

    Long getTimeoutDuration();

    TimeUnit getTimeoutUnit();

    Long getIntervalDuration();

    TimeUnit getIntervalUnit();

    Boolean isWaitForTrue();

    Boolean isWaitForNotNull();

    List<ResultValidator> getResultValidators();
}
