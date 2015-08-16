package shiver.me.timbers.waiting;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Karl Bennett
 */
class OptionsServiceInstantiater implements Instantiater<OptionsService, Wait> {

    private final Instantiater<OptionsService, Void> instantiater;
    private final OptionsConfigurer<Wait> optionsConfigurer;

    public OptionsServiceInstantiater(
        Instantiater<OptionsService, Void> instantiater,
        OptionsConfigurer<Wait> optionsConfigurer
    ) {
        this.instantiater = instantiater;
        this.optionsConfigurer = optionsConfigurer;
    }

    @Override
    public OptionsService instantiate(Wait wait)
        throws InstantiationException, InvocationTargetException, IllegalAccessException {
        return optionsConfigurer.apply(instantiater.instantiate(null), wait);
    }
}
