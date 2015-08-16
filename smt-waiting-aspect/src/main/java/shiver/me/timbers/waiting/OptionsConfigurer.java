package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
interface OptionsConfigurer<T> {

    OptionsService apply(OptionsService options, T configuration);
}
