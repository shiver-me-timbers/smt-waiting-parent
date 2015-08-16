package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
class SystemPropertyParser extends AbstractPropertyParser {
    public SystemPropertyParser() {
        super(new SystemPropertyGetter());
    }
}
