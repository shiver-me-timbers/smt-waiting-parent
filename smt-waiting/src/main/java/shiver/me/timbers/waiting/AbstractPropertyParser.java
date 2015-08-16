package shiver.me.timbers.waiting;

import java.util.EnumSet;

/**
 * @author Karl Bennett
 */
abstract class AbstractPropertyParser implements PropertyParser {

    private final PropertyGetter propertyGetter;

    public AbstractPropertyParser(PropertyGetter propertyGetter) {
        this.propertyGetter = propertyGetter;
    }

    @Override
    public Long getLongProperty(String key, long defaultValue) {
        return Long.valueOf(propertyGetter.get(key, defaultValue));
    }

    @Override
    public Boolean getBooleanProperty(String key, boolean defaultValue) {
        return Boolean.valueOf(propertyGetter.get(key, defaultValue));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends Enum<E>> E getEnumProperty(String key, E defaultValue) {
        final String value = propertyGetter.get(key, defaultValue);

        for (E enumValue : (EnumSet<E>) EnumSet.allOf((Class) defaultValue.getDeclaringClass())) {
            if (enumValue.name().equalsIgnoreCase(value)) {
                return enumValue;
            }
        }

        return defaultValue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getInstanceProperty(String key, T defaultValue) {

        final String value = propertyGetter.get(key);

        if (value == null) {
            return defaultValue;
        }

        try {
            return (T) Class.forName(value).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            return defaultValue;
        }
    }
}
