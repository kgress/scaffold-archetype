package ${package};

import io.github.kgress.scaffold.webdriver.WebDriverNavigation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ${package}.page.LoginPage;
import ${package}.page.InventoryPage;

/**
 * Page Navigation
 *
 * The navigation class is intended to encapsulate all web driver navigation into a single class. Any and all navigation
 * should be added here.
 *
 * Class Anatomy
 *
 * Above the class name, the annotation of @Component is required for Spring Boot to do any direct injection. In this case,
 * the base environment url is being pulled from the application.properties file. This annotation also allows us to inject
 * this navigation class into the BaseTest class.
 *
 * Extending off of {@link WebDriverNavigation} since it provides access to the web driver. This is what allows us to perform
 * the <pre>getWebDriverWrapper().get()</pre> in the {@link #navigateToLoginPage()} method.
 *
 * In the constructor, we reference <pre>{@code {@Value("${base-environment-url}") String baseEnvironmentUrl}</pre>.
 * This is a spring environment variable defined in application.properties. It's important to know that only Strings can be used for these variables.
 * Spring Boot also highly recommend to use class injection instead of composition whenever injecting into classes that are not
 * under the test directory.
 *
 * The {@link #navigateToLoginPage()} method is where the actual work happens. We get the WebDriverWrapper from the WebDriverNavigation class
 * and we perform the get() function to navigate to our base environment url. Then, we return a new instance of a ProfilePage page object.
 */
@Component
public class Navigation extends WebDriverNavigation {

    /**
     * The property for the base environment url
     */
    private final String baseEnvironmentUrl;

    /**
     * A constructor that injects the {@link #baseEnvironmentUrl} from application.properties
     *
     * @param baseEnvironmentUrl the {@link #baseEnvironmentUrl} as {@link String}. These must always be strings when using spring
     *                           variables.
     */
    public Navigation(@Value("${base-environment-url}") String baseEnvironmentUrl) {
        this.baseEnvironmentUrl = baseEnvironmentUrl;
    }

    /**
     * A navigation method for navigating to the login page, <a href>https://www.saucedemo.com/</a>
     *
     * Performs a selenium get(String) using the {@link #baseEnvironmentUrl}. Afterwards, builds a new {@link LoginPage}
     * and returns it.
     *
     * @return as {@link LoginPage}
     */
    public LoginPage navigateToLoginPage() {
        // Start the web test from the base environment url above
        getWebDriverWrapper().get(baseEnvironmentUrl);

        // Return the new instance of the LoginPage
        return new LoginPage();
    }

    /**
     * A navigation method for navigating to the Inventory Page, <a href>https://www.saucedemo.com/inventory.html</a>
     *
     * Creates a new instance of the {@link LoginPage} by hitting the {@link #navigateToLoginPage()} method. Afterwards,
     * performs a login action with the {@link LoginPage} using a provided username and password. Then, builds a new {@link InventoryPage}
     * and returns it.
     *
     * @param username the username as {@link String}
     * @param password the password as {@link String}
     * @return as {@link InventoryPage}
     */
    public InventoryPage navigateToInventoryPage(String username, String password) {
        // Create a new instance of the login page by navigating to it with the navigateToLoginPage method
        var loginPage = navigateToLoginPage();

        // With the new instance, invoke the login method from the login page
        loginPage.login(username, password);

        // After the login redirect occurs, return a new instance of the inventory page
        return new InventoryPage();
    }
}
