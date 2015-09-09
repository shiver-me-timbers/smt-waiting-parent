package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
public class SpringOptions extends Options {

    public SpringOptions() {
        super(
            new StaticDefaultChoices(),
            new PropertyParserChoices(new SpringPropertyParser()),
            new MergingManualChoices(),
            new BasicChooser(new ThreadSleeper())
        );
    }
}
