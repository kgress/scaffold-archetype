package ${package};

import ${package}.page.LoginPage;

/**
 * Base Page
 *
 * This is an example class on how an abstract class might be a useful tool for large projects that contain many page objects.
 *
 * For this example, this class contains a method {@link #isOnPage()}. The intention of this method is to have an extended class
 * override this method with its own implementation. In the case of the {@link LoginPage}, its implementation would be:
 *
 * <pre>{@code
 *     @Override
 *     public boolean isOnPage() {
 *         return credentialsInfo.isDisplayed();
 *     }
 * }</pre>
 *
 * The BasePage is not necessarily a required class and is meant as an example. If you do not wish to have the BasePage
 * on your project, simply remove this class and remove the implemented method from the {@link LoginPage}.
 */
public abstract class BasePage {

    /**
     * A method that allows extending classes to override based on their needs. In the future will consider implementing
     * this sort of functionality as a generic in Scaffold
     *
     * @return true or false based on the condition of the extending class.
     */
    protected abstract boolean isOnPage();
}
