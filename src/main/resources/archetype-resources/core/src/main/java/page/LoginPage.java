package ${package}.page;

import io.github.kgress.scaffold.webelements.ButtonWebElement;
import io.github.kgress.scaffold.webelements.DivWebElement;
import io.github.kgress.scaffold.webelements.InputWebElement;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * This class is an exmaple page object for sauce labs's login page located on https://www.saucedemo.com/.
 */
public class LoginPage extends BasePage {
    @Getter private InputWebElement usernameInput = new InputWebElement(By.id("user-name"));
    @Getter private InputWebElement passwordInput = new InputWebElement(By.id("password"));
    @Getter private ButtonWebElement submitButton = new ButtonWebElement(By.className("btn_action"));
    @Getter private DivWebElement credentialsInfo = new DivWebElement(By.id("login_credentials"));

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
    }
}
