package ${package}.pages;

import io.github.kgress.scaffold.webdriver.BasePage;
import io.github.kgress.scaffold.webelements.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Page Objects
 *
 * This class is an exmaple page object for sauce labs's inventory page located on <a href>https://www.saucedemo.com/inventory.html</a>.
 *
 * The page object is a simple representation of a web page using Scaffold's strongly typed elements and a By locator
 * from Selenium. Page objects should not contain assertions and should remain agnostic to any navigation or existence
 * of the web driver. Doing so will keep projects more maintainable. The page object is also a good place to create any helper
 * methods that pertain specifically to the page. For example, creating a click action or, in this case, an add item to car action
 * since this is a page object for an inventory page.
 *
 *
 * Class Anatomy
 *
 * In the below example we see three properties created in this way named {@link #inventoryList}, and {@link #inventoryHeader}.
 * These properties are creating a new identifier of their respective strongly typed element and located with their id or classname.
 * For your project, use a location method that works best for you. We also see the @Getter annotation. This annotation is from Lombok and
 * is used to automatically generate java getters at compile time; Therefore, you won't see any getters written in the code.
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
 * There are also a number of static final variables here. These are used in the location of item names, descriptions, prices, and for adding
 * items to the cart. This is an example of how you can use locators as strings inside of methods that create new instances of strongly
 * typed elements.
 *
 * We also see an {@link Override} for the {@link #isOnPage()} method. This is a method defined on {@link BasePage} and is intended to be overwritten. This
 * method gives us a little more creative freedom when it comes to Navigation with the ability to "verify" we've navigated to the page we've intended. The BasePage
 * is intended to provide an example on how we can potentially create more shared page functionality across all pages. In this method, isDisplayed() is a selenium
 * action that is used to ensure the {@link #inventoryHeader} is correctly appearing on the screen.
 *
 */
@Slf4j
public class InventoryPage extends BasePage {

    /**
     * The properties we've chosen to define as our page representation for the inventory page
     */
    @Getter private DivWebElement inventoryList = new DivWebElement(".inventory_list");
    @Getter private DivWebElement inventoryHeader = new DivWebElement(".header_secondary_container");
    private final static String INVENTORY_ITEM_NAME = "inventory_item_name";
    private final static String INVENTORY_ITEM_DESCRIPTION = "inventory_item_desc";
    private final static String INVENTORY_ITEM_PRICE = "inventory_item_price";
    private final static String ADD_TO_CART = "btn_primary";
    private final static String REMOVE = "btn_secondary";

    /**
     * An example of how to use a basic level of page verification
     *
     * @return true or false based on if the {@link #getInventoryHeader()} is displayed
     */
    @Override
    public boolean isOnPage() {
        return getInventoryHeader().isDisplayed();
    }

    /**
     * An example of how to use verifyIsOnPage() from BasePage. Every time the object is constructed,
     * we verify the automation is on the correct page with {@link #getInventoryHeader()}
     */
    public InventoryPage() {
        verifyIsOnPage(getInventoryHeader());
    }

    /**
     * Creates a new instance of a {@link DivWebElement} by finding the item number with the {@link #getItemNumber(int)} method and
     * returns an item name as {@link String}
     *
     * @param itemNumber the item number in the list of available items as {@link int}
     * @return the item name as {@link String}
     */
    public String getItemName(int itemNumber) {
        var itemName = getItemNumber(itemNumber).findElement(DivWebElement.class, INVENTORY_ITEM_NAME);
        return itemName.getText();
    }

    /**
     * Creates a new instance of a {@link DivWebElement} by finding the item number with the {@link #getItemNumber(int)} method and
     * returns an item description as {@link String}
     *
     * @param itemNumber the item number in the list of available items as {@link int}
     * @return the item description as {@link String}
     */
    public String getItemDescription(int itemNumber) {
        var itemDesc = getItemNumber(itemNumber).findElement(DivWebElement.class, INVENTORY_ITEM_DESCRIPTION);
        return itemDesc.getText();
    }

    /**
     * Creates a new instance of a {@link DivWebElement} by finding the item number with the {@link #getItemNumber(int)} method and
     * returns an item price as {@link String}
     *
     * @param itemNumber the item number in the list of available items as {@link int}
     * @return the item price as {@link String}
     */
    public String getItemPrice(int itemNumber) {
        var itemPrice = getItemNumber(itemNumber).findElement(DivWebElement.class, INVENTORY_ITEM_PRICE);
        return itemPrice.getText();
    }

    /**
     * Adds an item from the inventory page to the cart by creating a new instance of a {@link ButtonWebElement} with an item number
     * and the {@link #getItemName(int)} method.
     *
     * @param itemNumber the item number in the list of available items as {@link int}
     */
    public void addItemToCart(int itemNumber) {
        var addToCart = getItemNumber(itemNumber).findElement(ButtonWebElement.class, ADD_TO_CART);
        addToCart.click();
    }

    /**
     * A verification method for checking that an item has been added to the cart. We know the item will be in the cart if the
     * "Add the Cart" button changes to the "Remove" button.
     *
     * @param itemNumber the item number in the list of available items as {@link int}
     * @return the result as true or false {@link boolean}
     */
    public boolean itemAddedToCart(int itemNumber) {
        return getItemNumber(itemNumber).findElement(ButtonWebElement.class, REMOVE).isDisplayed();
    }

    /**
     * Returns the Div containing the item specified (zero-indexed)
     *
     * @param itemNumber the item to get as {@link int}
     * @return as {@link DivWebElement} for the item desired
     */
    private DivWebElement getItemNumber(int itemNumber) {
        var inventoryItemSelector = "div.inventory_item:nth-of-type(%d)";
        getInventoryList().getWait().waitUntilDisplayed();
        return getInventoryList().findElement(DivWebElement.class, String.format(inventoryItemSelector, itemNumber));
    }
}