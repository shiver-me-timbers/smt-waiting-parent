package shiver.me.timbers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("shiver.me.timbers.waiting")
@PropertySource("classpath:interval.properties")
public class WaiterIntervalConfiguration {
}
