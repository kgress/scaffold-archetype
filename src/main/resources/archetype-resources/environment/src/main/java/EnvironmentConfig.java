package ${package};

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = "${groupId}")
@PropertySource("classpath:/application.properties")
public class EnvironmentConfig {
}