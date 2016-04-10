package shiver.me.timbers.waiting;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingTimeoutClassFactory;
import shiver.me.timbers.waiting.property.SystemPropertyManager;
import shiver.me.timbers.waiting.test.WaitingPropertyRule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WaiterConfiguration.class)
public class ITAutoProxyAspectWaiterTimeoutPropertyClass extends AbstractITAspectWaiterTimeoutPropertyClass {

    @Rule
    public WaitingPropertyRule properties = new WaitingPropertyRule(new SystemPropertyManager());

    @Autowired
    private AutoProxyWaitingDefaultsClassFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingTimeoutClassFactory classFactory;

    @Override
    public WaitingPropertyRule properties() {
        return properties;
    }

    @Override
    public AutoProxyWaitingDefaultsClassFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingTimeoutClassFactory timeoutFactory() {
        return classFactory;
    }
}
