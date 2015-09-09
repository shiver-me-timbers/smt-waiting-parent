package shiver.me.timbers.waiting;

import java.util.List;

/**
 * @author Karl Bennett
 */
class PropertyParserChoices implements PropertyChoices {

    private final PropertyParser propertyParser;

    public PropertyParserChoices(PropertyParser propertyParser) {
        this.propertyParser = propertyParser;
    }

    @Override
    public Choices apply(Choices choices) {
        final List<ResultValidator> resultValidators = propertyParser.getInstanceProperty("smt.waiting.waitFor");
        resultValidators.addAll(choices.getResultValidators());

        return new BasicChoices(
            propertyParser.getLongProperty("smt.waiting.timeout.duration", choices.getTimeoutDuration()),
            propertyParser.getEnumProperty("smt.waiting.timeout.unit", choices.getTimeoutUnit()),
            propertyParser.getLongProperty("smt.waiting.interval.duration", choices.getIntervalDuration()),
            propertyParser.getEnumProperty("smt.waiting.interval.unit", choices.getIntervalUnit()),
            propertyParser.getBooleanProperty("smt.waiting.waitForTrue", choices.isWaitForTrue()),
            propertyParser.getBooleanProperty("smt.waiting.waitForNotNull", choices.isWaitForNotNull()),
            resultValidators
        );
    }
}
