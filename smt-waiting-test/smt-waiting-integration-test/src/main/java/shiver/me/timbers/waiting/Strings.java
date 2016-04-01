package shiver.me.timbers.waiting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class Strings {

    public static List<String> classNames(List objects) {
        if (objects == null) {
            return Collections.emptyList();
        }

        final List<String> names = new ArrayList<>();
        for (Object object : objects) {
            names.add(object.getClass().getName());
        }

        return names;
    }

    public static String concat(String delimiter, Object... objects) {
        if (objects == null || objects.length == 0) {
            return "";
        }

        return concat(new ArrayList<>(asList(objects)), delimiter);
    }

    public static String concat(List objects, String delimiter) {
        if (objects == null || objects.isEmpty()) {
            return "";
        }

        final StringBuilder builder = new StringBuilder(objects.remove(0).toString());

        for (Object object : objects) {
            builder.append(delimiter).append(object.toString());
        }

        return builder.toString();
    }
}
