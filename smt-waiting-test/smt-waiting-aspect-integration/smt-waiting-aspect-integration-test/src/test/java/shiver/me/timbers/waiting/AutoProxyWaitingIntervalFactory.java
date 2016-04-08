package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public class AutoProxyWaitingIntervalFactory extends WaitingIntervalFactory {

    @Autowired
    public AutoProxyWaitingIntervalFactory(CanChangeWaitingIntervalClassComponent canChangeWaitingIntervalClassComponent) {
        add(canChangeWaitingIntervalClassComponent, 200L, MILLISECONDS);
    }
}
