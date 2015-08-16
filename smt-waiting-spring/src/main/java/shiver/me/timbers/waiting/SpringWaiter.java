package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
public class SpringWaiter extends Waiter {

    public SpringWaiter() {
        this(new SpringOptions());
    }

    public SpringWaiter(SpringOptions options) {
        super(options);
    }
}
