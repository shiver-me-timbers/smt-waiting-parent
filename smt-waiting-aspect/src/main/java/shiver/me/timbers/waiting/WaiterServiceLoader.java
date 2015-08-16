package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
class WaiterServiceLoader extends ReflectionServiceLoader<WaiterService, OptionsService> {
    public WaiterServiceLoader() {
        super(
            new SingleInputInstantiater<>(
                new SPIServiceClassLoader<WaiterService, Waiter>(WaiterService.class).load(Waiter.class),
                new SPIServiceClassLoader<OptionsService, Options>(OptionsService.class).load(Options.class)
            )
        );
    }
}
