package shiver.me.timbers.waiting;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shiver.me.timbers.waiting.factory.AutoProxyClearWaitingIncludeMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.AutoProxyWaitingIncludeMethodFactory;
import shiver.me.timbers.waiting.property.SystemPropertyManager;
import shiver.me.timbers.waiting.test.WaitingPropertyRule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WaiterConfiguration.class)
public class ITAutoProxyAspectWaiterIncludePropertyMethod extends AbstractITAspectWaiterIncludePropertyMethod {

    @Rule
    public WaitingPropertyRule properties = new WaitingPropertyRule(new SystemPropertyManager());

    @Autowired
    private AutoProxyWaitingDefaultsMethodFactory defaultsFactory;

    @Autowired
    private AutoProxyWaitingIncludeMethodFactory excludesFactory;

    @Autowired
    private AutoProxyClearWaitingIncludeMethodFactory clearWaitingIncludeFactory;

    @Override
    public WaitingPropertyRule properties() {
        return properties;
    }

    @Override
    public AutoProxyWaitingDefaultsMethodFactory defaultsFactory() {
        return defaultsFactory;
    }

    @Override
    public AutoProxyWaitingIncludeMethodFactory includesFactory() {
        return excludesFactory;
    }

    @Override
    public AutoProxyClearWaitingIncludeMethodFactory clearIncludesFactory() {
        return clearWaitingIncludeFactory;
    }
}
