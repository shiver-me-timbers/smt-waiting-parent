package shiver.me.timbers.waiting.util;

import shiver.me.timbers.waiting.Options;

import java.util.List;

import static java.util.Arrays.asList;
import static shiver.me.timbers.waiting.util.Classes.toClasses;

public class Includes {

    public static Options addIncludes(Options options, Throwable... includes) {
        return addIncludes(options, asList(includes));
    }

    @SuppressWarnings("unchecked")
    public static Options addIncludes(Options options, List<Throwable> includes) {
        options.includes(toClasses(includes).toArray(new Class[includes.size()]));
        return options;
    }
}
