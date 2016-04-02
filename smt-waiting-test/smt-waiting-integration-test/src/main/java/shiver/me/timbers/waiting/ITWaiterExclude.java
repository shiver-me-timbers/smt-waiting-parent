package shiver.me.timbers.waiting;

import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface ITWaiterExclude {

    ExpectedException expectedException();

    WaitingExclude excludes(long duration, TimeUnit unit, Throwable... excludes);

    WaitingExclude excludesWithIncludes(long duration, TimeUnit unit, List<Throwable> excludes, List<Throwable> includes);

    void Cannot_ignore_exceptions_that_are_contained_in_the_exclude_list() throws Throwable;

    void Can_ignore_exceptions_that_are_not_contained_in_the_exclude_list() throws Throwable;

    void Cannot_ignore_exceptions_contained_in_the_exclude_list_and_not_in_the_include_list() throws Throwable;

    void Excludes_take_precedence_over_includes() throws Throwable;
}
