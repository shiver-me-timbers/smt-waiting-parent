package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
class WaitOptionsConfigurer implements OptionsConfigurer<Wait> {

    @Override
    public OptionsService apply(OptionsService options, Wait wait) {
        final TimeOut timeOut = wait.value();
        if (timeOut.duration() > -1) {
            options.withTimeOut(timeOut.duration(), timeOut.unit());
        }

        final Interval interval = wait.interval();
        if (interval.duration() > -1) {
            options.withInterval(interval.duration(), interval.unit());
        }

        final Class<? extends ResultValidator>[] waitFor = wait.waitFor();
        for (Class<? extends ResultValidator> validator : waitFor) {
            options.waitFor(newInstance(validator));
        }

        if (wait.waitForTrue()) {
            options.willWaitForTrue();
        }

        if (wait.waitForNotNull()) {
            options.willWaitForNotNull();
        }

        return options;
    }

    private <T> T newInstance(Class<T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
