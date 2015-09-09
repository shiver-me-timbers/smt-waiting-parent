package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
interface ManualChoices {

    Choices apply(Choices currentChoices, Choices manualChoices);
}
