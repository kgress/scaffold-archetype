# Page Objects
Scaffold implements the paradigm of a page object. Page objects are simple representations of web pages as a Java Object. In order to create a representation of a web page, you'll need to create a new class that contains properties
of Scaffold's strongly typed elements. Page Objects should always extend off of the class `BasePage`, as it provides common functionality to
Page Objects such as `verifyIsOnPage()`.

## Strongly Typed Elements
Scaffold makes available particular types of WebElements for you to use, which narrow the scope of methods available for any given WebElement, and keep the user focused on the actions they should be performing on these elements.
For example, a user cannot `sendKeys()` to a Button or a Link. The `ButtonWebElement` and `LinkWebElement` objects take that into account and don't expose those methods.

Another advantage of these elements is that they manage all interaction with the WebDriver internally. Most frameworks require the test write/page object maintainer to use the WebDriver to perform all their actions,
which requires a lot of Selenium knowledge, and can also lead to race conditions, thread-safety issues, and the exposure of unnecessary complexity to the testers.

In the millions of test cases run by Scaffold over the years, there has never been a reported occurrence of a `StaleElementReferenceException`. This is possible because the framework manages the WebDriver and the WebElements
internally, in a thread-safe and careful manner. It circumvents the possibility of a `StaleElementReferenceException` by always re-finding the element whenever an interaction occurs on it. This behavior is only possible when using Scaffold strongly typed elements, and not the raw Selenium element.
It is strongly recommended to always use Scaffold strongly typed elements in order to benefit from all Scaffold feature. Usage of raw Selenium objects will result in unexpected behavior.

A strongly typed element in scaffold can be one of the following:
* ButtonWebElement
* CheckBoxWebElement
* DateWebElement
* DivWebElement
* DropDownWebElement
* ImageWebElement
* InputWebElement
* LinkWebElement
* RadioWebElement
* StaticTextWebElement

## Page Object Example
Page objects should only be written in a way that makes them agnostic to navigation, the web driver itself, or by any other external means outside the scope of its own representation. They should also never contain any
assertions. While the `WebDriver` is not directly injected into a page object, the underlying `WebDriver` is used for finding elements when a `getElement()` is performed and _not_ when an element is declared.

In other words, simply initializing a strongly typed element does not perform an interaction with the underlying WebDriver; but instead, only creates a reference point with a By locator. This creates a dynamic use case when
performing navigation in that when a new page object is initialized, the elements will not be searched for at the time of the class being constructed. It isn't until you attempt to get the strongly typed element with a getter
that the underlying WebDriver will perform a `getWebElement()`, therefore performing the `findElement()` interaction.

To create a page object, follow the same design as Java Beans, [found here](https://www.javatpoint.com/java-bean).

The page objects should live within the core module in a page module. E.G: `core > src > main > java > your > groupID > page`

**An example page object:**
```java
@Getter
public class LoginPage extends BasePage {

    private final DivWebElement pageHeader = new DivWebElement(By.cssSelector("#someHeader"));
    private final InputWebElement emailInput = new InputWebElement(By.cssSelector("#emailInput"));
    private final InputWebElement passwordInput = new InputWebElement(By.cssSelector("#passwordInput"));
    private final ButtonWebElement loginButton = new ButtonWebElement(By.cssSelector("#loginButton"));
    
    public LoginPage() {
      verifyIsOnPage(getEmailInput(), getPasswordInput());
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }
    
    public void login(String username, String password) {
        getEmailInput().clearAndSendKeys(username);
        getPasswordInput().clearAndSendKeys(password);
        getLogInButton().click();
    }
}
```

Let's break down what we see in the example above.

1. `BasePage` extension
    1. All Page Objects should inherit functionality from `BasePage`. This provides access to functionality used to handle verification of the Page Object, along with access to the javascript executor and other useful methods.
2. Properties (or aka Java Fields)
    1. These are the elements on the page that we wish to represent. They can be whatever you feel is necessary to have. They could be headers, inputs, buttons, images, or anything else on the list of Strong Typed Elements above
       and are all merely references to be used later when you're getting the elements. They are located with the `By` class.
3. Getters
    1. Standard getter functions are auto generated with the lombok `@Getter` annotation from the class level. Any time we want to interact with an element, we
       always call the getter instead of the raw field.
4. Constructor
    1. The constructor should always behave like a default constructor, and not include any params. In addition, it should always handle the verification of the page by calling `BasePage`'s `verifyIsOnPage()` method.
5. Helper Functions
    1. The Page Object is a good opportunity to include any page specific actions you'd like to abstract. This is yet another level of creating an additional layer that will allow us to maintain our testing a little easier as it scales.

## Page Object Best Practices
As mentioned in the [Page Objects](#page-objects) section, it's best to instantiate new strongly typed elements at the class level with a "new" keyword. To expand on this,
it's recommended to never invoke `.findElement()` or `.findElements()` on a class variable. This creates an undesirable point of failure in the event the element(s)
cannot be found during the instantiation of the page; therefore, causing all tests that depend on the Page Object to fail instead of the tests that depend
on the element to fail.

Scaffold's Page Object performance is driven by the fact elements are not found during the instantiation of the page. Invoking `findElement()` or `findElements()`
bypasses this performance gain and the aforementioned point of failure design.

**An example of incorrect usage:**
```java
@Getter
public class LoginPage extends BasePage {

    private final DivWebElement pageHeader = findElement(DivWebElement.class, By.cssSelector("#someHeader"));
    private final InputWebElement emailInput = findElement(InputWebElement.class, By.cssSelector("#emailInput"));
    private final InputWebElement passwordInput = findElement(InputWebElement.class, By.cssSelector("#passwordInput"));
    private final ButtonWebElement loginButton = findElement(ButtonWebElement.class, By.cssSelector("#loginButton"));
    private final DivWebElement termsOfServiceListContainer = findElements(DivWebElement.class, By.cssSelector("#tos"));
    private final List<DivWebElement> termsOfServiceLinks = getTermsOfServiceListContainer.findElements(LinkWebElement.class, By.cssSelector("a"));
    
    public LoginPage() {
      verifyIsOnPage(getEmailInput(), getPasswordInput());
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }
    
    public void login(String username, String password) {
        getEmailInput().clearAndSendKeys(username);
        getPasswordInput().clearAndSendKeys(password);
        getLogInButton().click();
    }
}
```