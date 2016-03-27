package shiver.me.timbers.waiting;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class PropertyRule implements TestRule {

    private final PropertyManager propertyManager;
    private boolean disabled;

    public PropertyRule() {
        this(new PropertyManager());
    }

    public PropertyRule(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
        this.disabled = false;
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        if (disabled) {
            return base;
        }

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
        if (disabled) {
            return;
        }

        propertyManager.setProperty(key, value);
    }

    public void disabled(boolean disable) {
        this.disabled = disable;
    }
}
