package shiver.me.timbers.waiting.test;

import shiver.me.timbers.waiting.property.PropertyManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static shiver.me.timbers.waiting.util.Strings.classNames;
import static shiver.me.timbers.waiting.util.Strings.concat;

public class WaitingPropertyRule extends PropertyRule {

    public WaitingPropertyRule(PropertyManager propertyManager) {
        super(propertyManager);
    }

    public WaitingPropertyRule addTimeout(Long duration, TimeUnit unit) {
        setProperty("smt.waiting.timeout.duration", String.valueOf(duration));
        setProperty("smt.waiting.timeout.unit", unit.name());
        return this;
    }

    public WaitingPropertyRule addIncludesIfPresent(List<Throwable> includes) {
        if (includes == null || includes.isEmpty()) {
            return this;
        }

        setProperty("smt.waiting.includes", concat(classNames(includes), ","));
        return this;
    }

    public WaitingPropertyRule addExcludesIfPresent(List<Throwable> excludes) {
        if (excludes == null || excludes.isEmpty()) {
            return this;
        }

        setProperty("smt.waiting.excludes", concat(classNames(excludes), ","));
        return this;
    }
}
