package ${package}.page;

import ${package}.BasePage;
import io.github.kgress.scaffold.webelements.*;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * Page Objects
 *
 * This class is an exmaple page object for sauce labs's login page located on <a href>https://www.saucedemo.com/</a>.
 *
 * The page object is a simple representation of a web page using Scaffold's strongly typed elements and a By locator
 * from Selenium. Page objects should not contain assertions and should remain agnostic to any navigation or existence
 * of the web driver. Doing so will keep projects more maintainable. The page object is also a good place to create any helper
 * methods that pertain specifically to the page. For example, creating a click action or, in this case, a login action
 * since this is a page object for a login page.
 *
 *
 * Class Anatomy
 *
 * In the below example we see four properties created in this way called {@link #usernameInput},
 * {@link #passwordInput}, {@link #submitButton}, and {@link #credentialsInfo}. These properties are creating a new identifier
 * of their respective strongly typed element and located with their id or classname. For your project, use a location method
 * that works best for you. We also see the @Getter annotation. This annotation is from Lombok and is used to automatically generate
 * java getters at compile time; Therefore, you won't see any getters written in the code.
 *
 * The available strongly typed elements:
 * {@link ButtonWebElement}
 * {@link CheckBoxWebElement}
 * {@link DateWebElement}
 * {@link DivWebElement}
 * {@link DropDownWebElement}
 * {@link ImageWebElement}
 * {@link InputWebElement}
 * {@link LinkWebElement}
 * {@link RadioWebElement}
 * {@link StaticTextWebElement}
 *
 * For more information on strongly typed elements, check out scaffold's documentation here <a href>https://github.com/kgress/scaffold#page-objects</a>.
 *
 * We also see an {@link Override} for the {@link #isOnPage()} method. This is a method defined on {@link BasePage} and is intended to be overwritten. This
 * method gives us a little more creative freedom when it comes to Navigation with the ability to "verify" we've navigated to the page we've intended. The BasePage
 * is intended to provide an example on how we can potentially create more shared page functionality across all pages. In this method, isDisplayed() is a selenium
 * action that is used to ensure the {@link #credentialsInfo} is correctly appearing on the screen.
 *
 * At the end of this class we see the {@link #login(String, String)} method. This is a helper method that will be used with the navigation class and any
 * test classes. Since this page object is a representation of a login page, creating a login action is extremely helpful for any other class
 * that needs to use this action. In this method, sendKeys and click are selenium actions that are used to enter text in
 * to the {@link InputWebElement}s and click on the {@link ButtonWebElement}.
 */
public class LoginPage extends BasePage {

    /**
     * The properties we've chosen to define as our page representation for the login page
     */
    @Getter private InputWebElement usernameInput = new InputWebElement(By.id("user-name"));
    @Getter private InputWebElement passwordInput = new InputWebElement(By.id("password"));
    @Getter private ButtonWebElement submitButton = new ButtonWebElement(By.className("btn_action"));
    @Getter private DivWebElement credentialsInfo = new DivWebElement(By.id("login_credentials"));
    @Getter private DivWebElement loginErrorMessage = new DivWebElement(By.cssSelector("[data-test=error]"));

    /**
     * An example of how to use a basic level of page verification
     *
     * @return true or false based on if the {@link #credentialsInfo} is displayed
     */
    @Override
    public boolean isOnPage() {
        return getCredentialsInfo().isDisplayed();
    }

    public LoginPage() {
        isOnPage();
    }

    /**
     * Performs a login action with a provided username and password
     *
     * @param username the username as {@link String}
     * @param password the password as {@link String}
     */
    public void login(String username, String password) {
        // Input the username into the username field
        getUsernameInput().sendKeys(username);

        // Input the password into the password field
        getPasswordInput().sendKeys(password);

        // Click the submit button
        getSubmitButton().click();
    }
}
