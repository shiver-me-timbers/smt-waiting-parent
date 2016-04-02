package shiver.me.timbers.waiting;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface ITWaiterInclude {

    ExpectedException expectedException();

    WaitingInclude include(long duration, TimeUnit unit, Throwable... includes);

    WaitingInclude includeWithExclude(long duration, TimeUnit unit, List<Throwable> includes, List<Throwable> excludes);

    @Test
    void Can_ignore_exceptions_contained_in_the_include_list() throws Throwable;

    @Test
    void Cannot_ignore_exceptions_that_are_not_contained_in_the_include_list() throws Throwable;

    @Test
    void Can_ignore_all_exceptions_if_no_includes_set() throws Throwable;

    @Test
    void Can_ignore_exceptions_contained_in_the_include_list_and_not_in_the_exclude_list() throws Throwable;
}