package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;

/**
 * @author Karl Bennett
 */
@Configurable
class SpringPropertyGetter implements PropertyGetter {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public String get(String key, Object defaultValue) {

        if (applicationContext == null) {
            return defaultValue.toString();
        }

        return applicationContext.getEnvironment().getProperty(key, defaultValue.toString());
    }

    @Override
    public String get(String key) {

        if (applicationContext == null) {
            return null;
        }

        return applicationContext.getEnvironment().getProperty(key);
    }
}
