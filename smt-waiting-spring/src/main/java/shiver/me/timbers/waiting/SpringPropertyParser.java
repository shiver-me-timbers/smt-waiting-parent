package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
class SpringPropertyParser extends AbstractPropertyParser {
    public SpringPropertyParser() {
        super(new SpringPropertyGetter());
    }
}
