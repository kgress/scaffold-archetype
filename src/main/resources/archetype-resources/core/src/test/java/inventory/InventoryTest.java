package ${package}.inventory;

import ${package}.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is an example test class pertaining to the login page <a href>https://www.saucedemo.com/inventory.html</a>.
 * The following tests are merely examples of tests you'd consider writing.
 *
 * Ensure that all of your test classes are importing the junit5 {@link Test} annotation from junit.jupiter.api and not junit4.
 */
public class InventoryTest extends BaseTest {

    /**
     * An example of a test checking the sauce labs bolt t shirt label is correct
     */
    @Test
    public void testItem3Label() {
        // The expected name for the assertion later on
        var itemName = "Sauce Labs Bolt T-Shirt";

        // Navigate to the inventory page using the username and password data from the BaseTest file
        var inventoryPage = navigation.navigateToInventoryPage(USERNAME, PASSWORD);

        // Assert the inventory page's item name, for item number 3, matches the expected name from earlier.
        assertEquals(itemName, inventoryPage.getItemName(3), "The name for the Bolt T-Shirt should equal " + itemName);
    }

    @Test
    public void testItem3Price() {
        // The expected price for the assertion later on
        var itemPrice = "$15.99";

        // Navigate to the inventory page using the username and password data from the BaseTest file
        var inventoryPage = navigation.navigateToInventoryPage(USERNAME, PASSWORD);

        // Assert the inventory page's item price, for item number 3, matches the expected price from earlier.
        assertEquals(itemPrice, inventoryPage.getItemPrice(3), "The price for the Bolt T-Shirt should equal " + itemPrice);
    }

    @Test
    public void testItem3Description() {
        // The expected description for the assertion later on
        var itemDescription = "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.";

        // Navigate to the inventory page using the username and password data from the BaseTest file
        var inventoryPage = navigation.navigateToInventoryPage(USERNAME, PASSWORD);

        // Assert the inventory page's item description, for item number 3, matches the expected description from earlier.
        assertEquals(itemDescription, inventoryPage.getItemDescription(3), "The description for the Bolt T-Shirt should equal " + itemDescription);
    }

    @Test
    public void testAddOneItem() {
        // Navigate to the inventory page using the username and password data from the BaseTest file
        var inventoryPage = navigation.navigateToInventoryPage(USERNAME, PASSWORD);

        // Add item number 3 to the cart by invoking the addItemToCart(int) function
        inventoryPage.addItemToCart(3);

        // Assert the item has been added to the cart by invoking the itemAddedToCard(int) function
        assertTrue(inventoryPage.itemAddedToCart(3));
    }
}