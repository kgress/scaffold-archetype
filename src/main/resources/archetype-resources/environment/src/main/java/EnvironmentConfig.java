package ${package};

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * This class is a required spring boot configuration file for scanning all files in the project
 * that contain the {@link Component} and {@link Service} annotation. It's also used to create
 * specify the destination of the application's properties files. In addition, you can create
 * spring beans on this file using a method level annotation that can be injected into other portions
 * of the code base, if needed.
 */
@Configuration
@ComponentScan(value = "${groupId}")
@PropertySource({
    "classpath:/application.properties"
})
public class EnvironmentConfig {

}