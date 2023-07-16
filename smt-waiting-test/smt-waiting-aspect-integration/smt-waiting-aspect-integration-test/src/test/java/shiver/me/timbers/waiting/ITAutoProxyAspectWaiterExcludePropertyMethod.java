package shiver.me.timbers.waiting;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shiver.me.timbers.waiting.factory.AutoProxyClearWaitingExcludeMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingExcludeMethodFactory;
import shiver.me.timbers.waiting.property.SystemPropertyManager;
import shiver.me.timbers.waiting.test.WaitingPropertyRule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WaiterConfiguration.class)
public class ITAutoProxyAspectWaiterExcludePropertyMethod extends AbstractITAspectWaiterExcludePropertyMethod {

    @Rule
    public WaitingPropertyRule properties = new WaitingPropertyRule(new SystemPropertyManager());

    @Autowired
    private AutoProxyWaitingDefaultsMethodFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingExcludeMethodFactory excludesFactory;

    @Autowired
    private AutoProxyClearWaitingExcludeMethodFactory clearExcludesFactory;

    @Override
    public WaitingPropertyRule properties() {
        return properties;
    }

    @Override
    public AutoProxyWaitingDefaultsMethodFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingExcludeMethodFactory excludesFactory() {
        return excludesFactory;
    }

    @Override
    public AutoProxyClearWaitingExcludeMethodFactory clearExcludesFactory() {
        return clearExcludesFactory;
    }
}
