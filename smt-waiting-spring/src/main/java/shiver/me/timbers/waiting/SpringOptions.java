package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
public class SpringOptions extends Options {

    public SpringOptions() {
        super(new ThreadSleeper(), new SpringPropertyParser());
    }
}
