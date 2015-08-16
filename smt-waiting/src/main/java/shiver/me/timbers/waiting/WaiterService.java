package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
interface WaiterService {

    <T> T wait(Until<T> until);
}
