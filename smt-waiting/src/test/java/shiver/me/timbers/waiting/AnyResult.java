package shiver.me.timbers.waiting;

public class AnyResult implements ResultValidator {
    @Override
    public boolean isValid(Object result) throws Throwable {
        return true;
    }
}
