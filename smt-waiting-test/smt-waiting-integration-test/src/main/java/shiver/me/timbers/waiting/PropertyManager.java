package shiver.me.timbers.waiting;

/**
 * @author Karl Bennett
 */
public interface PropertyManager {
    void setProperty(String key, String value);

    void restoreProperties();
}
