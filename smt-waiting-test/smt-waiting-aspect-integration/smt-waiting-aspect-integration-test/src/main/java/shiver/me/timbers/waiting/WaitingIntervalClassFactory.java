package shiver.me.timbers.waiting;

public class WaitingIntervalClassFactory extends WaitingIntervalFactory {

    public WaitingIntervalClassFactory() {
        this(new CanChangeWaitingIntervalClass());
    }

    public WaitingIntervalClassFactory(CanChangeWaitingIntervalClass canChangeWaitingIntervalClass) {
        super(canChangeWaitingIntervalClass);
    }
}
