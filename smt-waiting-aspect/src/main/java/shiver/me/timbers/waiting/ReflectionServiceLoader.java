package shiver.me.timbers.waiting;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Karl Bennett
 */
class ReflectionServiceLoader<S, I> implements ServiceLoader<S, I> {

    private final Instantiater<S, I> instantiater;

    public ReflectionServiceLoader(Instantiater<S, I> instantiater) {
        this.instantiater = instantiater;
    }

    @Override
    public S load(I input) {
        try {
            return instantiater.instantiate(input);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
