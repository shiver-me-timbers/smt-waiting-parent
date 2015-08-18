package shiver.me.timbers.waiting;

import java.util.HashMap;
import java.util.Map;

class PropertyManager {

    private final Map<String, String> properties = new HashMap<>();

    public void setProperty(String key, String value) {
        backupProperty(key);
        System.setProperty(key, value);
    }

    private void backupProperty(String key) {
        properties.put(key, System.getProperty(key));
    }

    public void restoreProperties() {
        for (String key : properties.keySet()) {
            restoreProperty(key);
        }
    }

    private void restoreProperty(String key) {
        String value = properties.get(key);

        if (value == null) {
            System.clearProperty(key);
            return;
        }

        System.setProperty(key, value);
    }
}
