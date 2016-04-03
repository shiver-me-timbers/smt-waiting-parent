package shiver.me.timbers.waiting;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringWaiterConfiguration.class)
public abstract class AbstractITSpringWaiterIncludeProperty extends AbstractITWaiterIncludeProperty {

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
