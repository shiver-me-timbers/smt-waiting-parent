package shiver.me.timbers.waiting;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.emptyList;

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

        final EnumSet<E> enums = EnumSet.allOf((Class) defaultValue.getDeclaringClass());
        for (E enumValue : enums) {
            if (enumValue.name().equalsIgnoreCase(value)) {
                return enumValue;
            }
        }

        throw new IllegalStateException(format(
            "Invalid %s value supplied of (%s). Allowed values are %s.", key, value, enums
        ));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getInstanceProperty(String key) {

        final String value = propertyGetter.get(key);

        if (value == null) {
            return emptyList();
        }

        final String[] types = value.split(",");

        final List<T> instances = new ArrayList<>();
        for (String type : types) {
            try {
                instances.add((T) Class.forName(type).newInstance());
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(
                    format(
                        "The property value for (%s) must be a comma separated list of fully qualified class names.",
                        key
                    ),
                    e
                );
            } catch (InstantiationException | IllegalAccessException e) {
                throw new IllegalStateException(
                    format("Th class (%s) in the property value for (%s) cannot be instantiated.", type, key),
                    e
                );
            }
        }
        return instances;
    }
}
