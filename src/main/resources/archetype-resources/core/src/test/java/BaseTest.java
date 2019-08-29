package ${package};

import io.github.kgress.scaffold.environment.config.ScaffoldConfiguration;
import io.github.kgress.scaffold.webdriver.ScaffoldBaseTest;
import io.github.kgress.scaffold.extensions.SauceExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Base Test
 *
 * The base test file is used to include any project specific spring boot wiring or configurations along with the Scaffold configuration.
 * By extending off of {@link ScaffoldConfiguration}, all web driver instantiation and tear down is handled automatically. Therefore,
 * all test classes should be extending off of this class.
 *
 * Class Anatomy
 *
 * The annotations are important for configuring the execution method (what was once known in Junit4 as the test runner), the parallelization mode,
 * and the spring configuration for loading the application context / spring boot test app for Scaffold and your own project. Since we don't
 * need a web environment, it's also useful to set this to none.
 *
 * Since the base test file shouldn't have any testing in it, it's important to declare this class abstract so any Junit5 methods are not ran as tests.
 * Extending ScaffoldBaseTest also gets you the driver initialization and tear down and will also get you the WebDriverWrapper instance from the current thread.
 *
 * These protected static final {@link String}'s are test data variables used in at least more than one test file. In this case,
 * the username and password are required for login page tests and inventory page tests. Since both of those test files extend
 * off of BaseTest, it's best to put those data points in this class.
 *
 * The <prep>@Autowired</prep> annotation is a means of dependency injection within Spring Boot, with this sort of usage known as composition.
 * With this annotation, we can wire in an instance of a <prep>@Component</prep> with ease. Since it's protected, any class that
 * extends off of BaseTest will now be able to access everything from ScaffoldBaseTest and the Navigation class from your own project.
 */
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({SpringExtension.class, SauceExtension.class})
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = { EnvironmentConfig.class, ScaffoldConfiguration.class }
)
public abstract class BaseTest extends ScaffoldBaseTest {

    // Shared constant variables that are used in the LoginTest and InventoryTest files
    protected static final String USERNAME = "standard_user";
    protected static final String PASSWORD = "secret_sauce";

    /**
     * The direct injection of the {@link Navigation} class that contains all of our web driver navigation. Because this is located
     * in the test directory, injection by composition is best practice.
     */
    @Autowired
    protected Navigation navigation;
}