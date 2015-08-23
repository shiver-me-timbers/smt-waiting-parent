package shiver.me.timbers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@Configuration
@ComponentScan("shiver.me.timbers.waiting")
@EnableSpringConfigured
@PropertySource("classpath:interval.properties")
public class WaiterIntervalConfiguration {
}
