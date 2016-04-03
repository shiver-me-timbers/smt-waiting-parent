package shiver.me.timbers.waiting;

import java.util.ArrayList;
import java.util.List;

public class Classes {

    public static List<Class> toClasses(List objects) {
        final List<Class> classes = new ArrayList<>(objects.size());
        for (Object object : objects) {
            classes.add(object.getClass());
        }
        return classes;
    }
}
