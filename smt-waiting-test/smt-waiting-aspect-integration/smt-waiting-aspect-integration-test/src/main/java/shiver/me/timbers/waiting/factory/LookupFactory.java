package shiver.me.timbers.waiting.factory;

public interface LookupFactory<T> {

    T find(Object... args);

    void add(T object, Object... args);
}
