package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingTimeoutFactory;

public interface WaitingTimeoutFactoryAware {
    WaitingTimeoutFactory timeoutFactory();
}
