package shiver.me.timbers.waiting;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shiver.me.timbers.waiting.factory.AutoProxyClearWaitingExcludeClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingExcludeClassFactory;
import shiver.me.timbers.waiting.property.SystemPropertyManager;
import shiver.me.timbers.waiting.test.WaitingPropertyRule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WaiterConfiguration.class)
public class ITAutoProxyAspectWaiterExcludePropertyClass extends AbstractITAspectWaiterExcludePropertyClass {

    @Rule
    public WaitingPropertyRule properties = new WaitingPropertyRule(new SystemPropertyManager());

    @Autowired
    private AutoProxyWaitingDefaultsClassFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingExcludeClassFactory excludesFactory;

    @Autowired
    private AutoProxyClearWaitingExcludeClassFactory clearExcludesFactory;

    @Override
    public WaitingPropertyRule properties() {
        return properties;
    }

    @Override
    public AutoProxyWaitingDefaultsClassFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingExcludeClassFactory excludesFactory() {
        return excludesFactory;
    }

    @Override
    public AutoProxyClearWaitingExcludeClassFactory clearExcludesFactory() {
        return clearExcludesFactory;
    }
}
