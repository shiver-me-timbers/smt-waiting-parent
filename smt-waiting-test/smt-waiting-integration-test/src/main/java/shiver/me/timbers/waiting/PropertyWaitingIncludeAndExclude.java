package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public abstract class PropertyWaitingIncludeAndExclude implements WaitingInclude, WaitingExclude {

    private final WaitingPropertyRule properties;
    private final Long duration;
    private final TimeUnit unit;
    private final List<Throwable> includes;
    private final List<Throwable> excludes;

    public PropertyWaitingIncludeAndExclude(
        WaitingPropertyRule properties,
        Long duration,
        TimeUnit unit,
        List<Throwable> includes,
        List<Throwable> excludes
    ) {
        this.properties = properties;
        this.duration = duration;
        this.unit = unit;
        this.includes = includes;
        this.excludes = excludes;
    }

    protected abstract <T> T method(Callable<T> callable) throws Exception;

    @Override
    public <T> T includeMethod(Callable<T> callable) throws Exception {
        return includeAndExcludeMethod(callable);
    }

    @Override
    public <T> T excludeMethod(Callable<T> callable) throws Exception {
        return includeAndExcludeMethod(callable);
    }

    private <T> T includeAndExcludeMethod(Callable<T> callable) throws Exception {
        properties.addTimeout(duration, unit);
        properties.addIncludesIfPresent(includes);
        properties.addExcludesIfPresent(excludes);
        return method(callable);
    }
}
