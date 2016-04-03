package shiver.me.timbers.waiting;

public interface LookupFactory<T> {

    T find(Object... args);

    void add(T object, Object... args);
}
