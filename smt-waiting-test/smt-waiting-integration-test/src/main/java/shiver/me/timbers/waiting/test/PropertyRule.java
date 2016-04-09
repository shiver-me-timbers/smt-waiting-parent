package shiver.me.timbers.waiting.test;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import shiver.me.timbers.waiting.property.PropertyManager;

public class PropertyRule implements TestRule {

    private PropertyManager propertyManager;

    public PropertyRule(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    base.evaluate();
                } finally {
                    propertyManager.restoreProperties();
                }
            }
        };
    }

    public void setProperty(String key, String value) {
        propertyManager.setProperty(key, value);
    }

    public void setPropertyManager(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
    }
}
