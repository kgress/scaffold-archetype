package ${package};

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Environment Config
 *
 * This class is a required spring boot file for scanning all files in the project that contain the {@link Component} and
 * {@link Service} annotation. It's also used to create additional spring beans using a method level annotation that
 * can be injected into other portions of the code base.
 *
 * For example, if your project required an implementation of an api and you need a modified {@link ObjectMapper}, the instantiation of it should
 * occur in this class as a bean. Then, in your service layer for the required api, you could inject this same object mapper
 * using constructor level injection. For more information about injection, check out the spring documentation here
 * <a href>https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-spring-beans-and-dependency-injection.html</a>
 */
@Configuration
@ComponentScan(value = "${groupId}")
@PropertySource("classpath:/application.properties")
public class EnvironmentConfig {

    /**
     * An example of how to create an {@link ObjectMapper} using the {@link Bean} method level annotation
     *
     * @return the {@link ObjectMapper}
     */
    @Bean
    public ObjectMapper exampleObjectMapper() {
        // A default object mapper is already provided in application context. Here, you'd customize and return your own
        return new ObjectMapper();
    }
}