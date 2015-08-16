package shiver.me.timbers.waiting;

import java.util.Iterator;

/**
 * @author Karl Bennett
 */
class SPIServiceClassLoader<S, I extends S> implements ServiceLoader<Class<S>, Class<I>> {

    private final Class<S> type;

    public SPIServiceClassLoader(Class<S> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<S> load(Class<I> defaultType) {
        final Iterator<S> loaderIterator = java.util.ServiceLoader.load(type).iterator();
        if (loaderIterator.hasNext()) {
            return (Class<S>) loaderIterator.next().getClass();
        }

        return (Class<S>) defaultType;
    }
}
