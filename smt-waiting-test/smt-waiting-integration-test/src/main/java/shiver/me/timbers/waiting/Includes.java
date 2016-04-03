package shiver.me.timbers.waiting;

import java.util.List;

import static java.util.Arrays.asList;

class Includes {

    public static Options addIncludes(Options options, Throwable... includes) {
        return addIncludes(options, asList(includes));
    }

    public static Options addIncludes(Options options, List<Throwable> includes) {
        for (Throwable include : includes) {
            options.include(include.getClass());
        }

        return options;
    }
}
