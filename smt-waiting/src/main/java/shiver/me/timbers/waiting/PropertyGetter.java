package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
interface PropertyGetter {

    String get(String key, Object defaultValue);

    String get(String key);
}
