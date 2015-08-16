package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
interface ServiceLoader<S, I> {

    S load(I input);
}
