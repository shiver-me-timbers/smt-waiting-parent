package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
class SystemPropertyGetter implements PropertyGetter {

    @Override
    public String get(String key, Object defaultValue) {
        return System.getProperty(key, defaultValue.toString());
    }

    @Override
    public String get(String key) {
        return System.getProperty(key);
    }
}
