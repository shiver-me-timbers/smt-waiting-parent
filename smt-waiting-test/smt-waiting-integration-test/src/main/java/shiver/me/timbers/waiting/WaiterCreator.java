package shiver.me.timbers.waiting;

public class WaiterCreator<O extends Options, W extends Waiter> {

    @SuppressWarnings("unchecked")
    public O options() {
        return (O) new Options();
    }

    @SuppressWarnings("unchecked")
    public W waiter() {
        return (W) new Waiter();
    }

    @SuppressWarnings("unchecked")
    public W waiter(O options) {
        return (W) new Waiter(options);
    }
}
