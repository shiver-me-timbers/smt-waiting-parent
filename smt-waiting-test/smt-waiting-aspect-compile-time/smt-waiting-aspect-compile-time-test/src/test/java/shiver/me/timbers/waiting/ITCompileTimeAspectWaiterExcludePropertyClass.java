package shiver.me.timbers.waiting;

import org.junit.Rule;
import shiver.me.timbers.waiting.property.SystemPropertyManager;
import shiver.me.timbers.waiting.test.WaitingPropertyRule;

public class ITCompileTimeAspectWaiterExcludePropertyClass extends AbstractITAspectWaiterExcludePropertyClass {

    @Rule
    public WaitingPropertyRule properties = new WaitingPropertyRule(new SystemPropertyManager());

    @Override
    public WaitingPropertyRule properties() {
        return properties;
    }
}
