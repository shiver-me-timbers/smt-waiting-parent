package shiver.me.timbers.waiting.factory;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Arrays.asList;

public class MapLookupFactory<T> implements LookupFactory<T> {

    private final Map<Object, T> map;

    public MapLookupFactory() {
        this(new ConcurrentHashMap<Object, T>());
    }

    public MapLookupFactory(Map<Object, T> map) {
        this.map = map;
    }

    @Override
    public T find(Object... args) {
        final T result = map.get(asList(args));

        if (result != null) {
            return result;
        }

        throw new IllegalArgumentException("No object found for args " + Arrays.toString(args));
    }

    @Override
    public void add(T object, Object... args) {
        map.put(asList(args), object);
    }
}
