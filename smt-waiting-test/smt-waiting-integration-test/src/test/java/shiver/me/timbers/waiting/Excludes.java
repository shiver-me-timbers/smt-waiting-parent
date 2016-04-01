package shiver.me.timbers.waiting;

import java.util.List;

import static java.util.Arrays.asList;

class Excludes {

    public static void addExcludes(Options options, Throwable... excludes) {
        addExcludes(options, asList(excludes));
    }

    public static void addExcludes(Options options, List<Throwable> excludes) {
        for (Throwable include : excludes) {
            options.exclude(include.getClass());
        }
    }
}
