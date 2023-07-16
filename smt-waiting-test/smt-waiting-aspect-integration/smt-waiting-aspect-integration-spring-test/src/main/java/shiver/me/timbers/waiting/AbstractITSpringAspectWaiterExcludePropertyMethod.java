package shiver.me.timbers.waiting;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shiver.me.timbers.waiting.property.DynamicPropertySource;
import shiver.me.timbers.waiting.test.WaitingPropertyRule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringAspectWaiterConfiguration.class)
public abstract class AbstractITSpringAspectWaiterExcludePropertyMethod extends AbstractITAspectWaiterExcludePropertyMethod {

    @Autowired
    private DynamicPropertySource dynamicPropertySource;

    @Rule
    public WaitingPropertyRule properties = new WaitingPropertyRule(null);

    @Before
    public void setUp() {
        properties.setPropertyManager(dynamicPropertySource);
    }

    @Override
    public WaitingPropertyRule properties() {
        return properties;
    }
}
