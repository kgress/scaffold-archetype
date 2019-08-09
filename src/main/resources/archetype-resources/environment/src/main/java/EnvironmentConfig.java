package ${package}.environment;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = ${artifactId})
@PropertySource("classpath:/application.properties")
public class EnvironmentConfig {
}