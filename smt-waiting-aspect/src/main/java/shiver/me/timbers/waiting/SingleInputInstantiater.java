package shiver.me.timbers.waiting;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Karl Bennett
 */
class SingleInputInstantiater<T, I> implements Instantiater<T, I> {

    private final Constructor<T> constructor;

    public SingleInputInstantiater(Class<T> type, Class<I> inputType) {
        try {
            this.constructor = type.getConstructor(inputType);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public T instantiate(I input)
        throws InstantiationException, InvocationTargetException, IllegalAccessException {
        return constructor.newInstance(input);
    }
}
