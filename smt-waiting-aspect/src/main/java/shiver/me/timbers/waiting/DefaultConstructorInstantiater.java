package shiver.me.timbers.waiting;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Karl Bennett
 */
class DefaultConstructorInstantiater<T> implements Instantiater<T, Void> {

    private final Constructor<T> constructor;

    public DefaultConstructorInstantiater(Class<T> type) {
        try {
            this.constructor = type.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public T instantiate(Void notUsed)
        throws InstantiationException, InvocationTargetException, IllegalAccessException {
        return constructor.newInstance();
    }
}
