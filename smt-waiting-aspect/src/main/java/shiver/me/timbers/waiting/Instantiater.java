package shiver.me.timbers.waiting;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Karl Bennett
 */
interface Instantiater<T, I> {

    T instantiate(I input) throws InstantiationException, InvocationTargetException, IllegalAccessException;
}
