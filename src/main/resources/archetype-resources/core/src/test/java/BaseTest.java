package ${package};

import io.github.kgress.scaffold.environment.config.ScaffoldConfiguration;
import io.github.kgress.scaffold.ScaffoldBaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * This file is used to include any project specific spring boot wiring or configurations along with
 * the Scaffold configuration. By extending off of {@link ScaffoldConfiguration}, all web driver
 * instantiation and tear down is handled automatically. Therefore, all test classes should extend
 * this file, BaseTest.
 * <p>
 * Class Anatomy
 * <p>
 * The annotations are important for configuring our testing environment:
 * <p>
 * - Slf4j is added to this class to allow for any additional logging in a test.
 * - Execution is added to this class for configuring execution settings. In this case, setting
 *   execution mode to concurrent so all tests are ran in parallell.
 * - ExtendWith is added to provide additional access to the Spring Boot application context.
 *   Scaffold provides a Sauce extension if testing against a SauceLabs environment. To use it,
 *   replace SpringExtension.class with SauceExtension.class.
 * - SpringBootTest is added here to provided additional configuration of the Spring Boot
 *   environment. It sets the WebEnvirnoment to none and loads in configuration classes for
 *   Scaffold and the local project.
 */
@Slf4j
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(SpringExtension.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.NONE,
    classes = {EnvironmentConfig.class, ScaffoldConfiguration.class}
)
public abstract class BaseTest extends ScaffoldBaseTest {

  /**
   * Spring Boot injection of the Navigation method at the BaseTest level for all child test files
   * to inherit. Uses composition to inject the navigation class, instead of constructor level. This
   * is because we are in a test context, and composition is preferred.
   */
  @Autowired
  protected Navigation navigation;
}