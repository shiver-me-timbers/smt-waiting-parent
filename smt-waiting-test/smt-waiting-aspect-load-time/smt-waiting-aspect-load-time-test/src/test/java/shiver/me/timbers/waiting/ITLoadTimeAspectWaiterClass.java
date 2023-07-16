package shiver.me.timbers.waiting;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WaiterConfiguration.class)
public class ITLoadTimeAspectWaiterClass extends AbstractITAspectWaiterClass {
}
