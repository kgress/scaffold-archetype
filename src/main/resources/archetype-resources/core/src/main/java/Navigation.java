package ${package};

import io.github.kgress.scaffold.WebDriverNavigation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This file is intended to encapsulate all web driver navigation into a single class.
 * Any and all navigation should be added here. Each navigation method should be responsible for
 * performing a navigation and returning a new Page Object.
 * <p>
 * For Example:
 * <pre>
 *   {@code
 *   public LoginPage navigateToHomePage() {
 *       getWebDriverWrapper().get(baseEnvironmentUrl);
 *       return new HomePage();
 *   }
 *   }
 * </pre>
 * <p>
 * In the above example, the navigation method is responsible for invoking the baseEnvironmentUrl
 * and then returning the representation of that URL, the HomePage.
 * <p>
 * Class Anatomy:
 * <p>
 * Above the class name, the annotation of @Component is required for Spring Boot to do any direct
 * injection. In this case, the base environment url is being pulled from the application.properties
 * file. This annotation also allows us to inject this navigation class into the BaseTest class.
 * <p>
 * Extending off of {@link WebDriverNavigation} since it provides access to the web driver. This is
 * what allows us to perform the <pre>getWebDriverWrapper().get()</pre> in the code example above
 * <p>
 * In the constructor, we reference <pre>{@code {@Value("${base-environment-url}") String
 * baseEnvironmentUrl}</pre>. This is a spring environment variable defined in
 * application.properties. It's important to know that only Strings can be used for these variables.
 * Spring Boot also highly recommend to use class injection instead of composition whenever
 * injecting into classes that are not under the test directory.
 */
@Component
public class Navigation extends WebDriverNavigation {

  private final String baseEnvironmentUrl;

  /**
   * A constructor that injects the {@link #baseEnvironmentUrl} from application.properties
   *
   * @param baseEnvironmentUrl the {@link #baseEnvironmentUrl} as {@link String}.
   */
  public Navigation(@Value("${base-environment-url}") String baseEnvironmentUrl) {
    this.baseEnvironmentUrl = baseEnvironmentUrl;
  }
}