package shiver.me.timbers.waiting.util;

import shiver.me.timbers.waiting.Options;

import java.util.List;

import static java.util.Arrays.asList;
import static shiver.me.timbers.waiting.util.Classes.toClasses;

public class Excludes {

    public static Options addExcludes(Options options, Throwable... excludes) {
        return addExcludes(options, asList(excludes));
    }

    @SuppressWarnings("unchecked")
    public static Options addExcludes(Options options, List<Throwable> excludes) {
        options.excludes(toClasses(excludes).toArray(new Class[excludes.size()]));
        return options;
    }
}
