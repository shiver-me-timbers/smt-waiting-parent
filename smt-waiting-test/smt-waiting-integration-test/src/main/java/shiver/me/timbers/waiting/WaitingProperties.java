package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

class WaitingProperties {

    static void addTimeout(PropertyRule properties, long duration, TimeUnit unit) {
        properties.setProperty("smt.waiting.timeout.duration", String.valueOf(duration));
        properties.setProperty("smt.waiting.timeout.unit", unit.name());
    }
}
