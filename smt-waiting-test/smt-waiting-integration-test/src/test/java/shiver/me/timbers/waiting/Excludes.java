package shiver.me.timbers.waiting;

import java.util.List;

import static java.util.Arrays.asList;

class Excludes {

    public static Options addExcludes(Options options, Throwable... excludes) {
        return addExcludes(options, asList(excludes));
    }

    public static Options addExcludes(Options options, List<Throwable> excludes) {
        for (Throwable include : excludes) {
            options.exclude(include.getClass());
        }

        return options;
    }
}
