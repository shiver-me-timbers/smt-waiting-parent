package shiver.me.timbers.waiting.property;

import org.springframework.core.env.PropertySource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicPropertySource extends PropertySource<Map<String, String>> implements PropertyManager {

    public DynamicPropertySource() {
        this(new ConcurrentHashMap<String, String>());
    }

    public DynamicPropertySource(Map<String, String> source) {
        super("mapPropertySource", source);
    }

    @Override
    public String getProperty(String key) {
        return getSource().get(key);
    }

    @Override
    public void setProperty(String key, String value) {
        getSource().put(key, value);
    }

    @Override
    public void restoreProperties() {
        getSource().clear();
    }
}
