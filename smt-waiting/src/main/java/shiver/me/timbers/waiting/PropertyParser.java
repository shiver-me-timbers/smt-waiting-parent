package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
interface PropertyParser {

    Long getLongProperty(String key, long defaultValue);

    Boolean getBooleanProperty(String key, boolean defaultValue);

    <E extends Enum<E>> E getEnumProperty(String key, E defaultValue);

    <T> T getInstanceProperty(String key, T defaultValue);
}
