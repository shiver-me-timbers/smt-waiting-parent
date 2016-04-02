package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static shiver.me.timbers.waiting.Strings.classNames;
import static shiver.me.timbers.waiting.Strings.concat;

class WaitingProperties {

    static void addTimeout(PropertyRule properties, long duration, TimeUnit unit) {
        properties.setProperty("smt.waiting.timeout.duration", String.valueOf(duration));
        properties.setProperty("smt.waiting.timeout.unit", unit.name());
    }

    static void addIncludesIfPresent(PropertyRule properties, List<Throwable> includes) {
        if (includes == null || includes.isEmpty()) {
            return;
        }

        properties.setProperty("smt.waiting.include", concat(classNames(includes), ","));
    }

    static void addExcludesIfPresent(PropertyRule properties, List<Throwable> excludes) {
        if (excludes == null || excludes.isEmpty()) {
            return;
        }

        properties.setProperty("smt.waiting.exclude", concat(classNames(excludes), ","));
    }
}
