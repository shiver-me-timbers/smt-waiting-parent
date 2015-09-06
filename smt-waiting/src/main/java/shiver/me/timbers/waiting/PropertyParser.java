package shiver.me.timbers.waiting;

import java.util.List;

/**
 * @author Karl Bennett
 */
interface PropertyParser {

    Long getLongProperty(String key, long defaultValue);

    Boolean getBooleanProperty(String key, boolean defaultValue);

    <E extends Enum<E>> E getEnumProperty(String key, E defaultValue);

    <T> List<T> getInstanceProperty(String key);
}
