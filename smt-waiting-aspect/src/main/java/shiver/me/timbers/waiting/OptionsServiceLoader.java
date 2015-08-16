package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
class OptionsServiceLoader extends ReflectionServiceLoader<OptionsService, Wait> {
    public OptionsServiceLoader() {
        super(
            new OptionsServiceInstantiater(
                new DefaultConstructorInstantiater<>(
                    new SPIServiceClassLoader<OptionsService, Options>(OptionsService.class).load(Options.class)
                ),
                new WaitOptionsConfigurer()
            )
        );
    }
}
