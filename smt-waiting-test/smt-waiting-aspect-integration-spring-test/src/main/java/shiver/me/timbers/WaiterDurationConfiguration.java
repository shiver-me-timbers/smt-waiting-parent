package shiver.me.timbers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@Configuration
@ComponentScan("shiver.me.timbers.waiting.configuration")
@EnableSpringConfigured
@PropertySource("classpath:duration.properties")
public class WaiterDurationConfiguration {
}
