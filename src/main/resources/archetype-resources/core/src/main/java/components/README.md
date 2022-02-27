# Components
Components are similar to Page Objects in that they define specific properties of a website. What makes them different is that they are intended
to be properties of a website that are shared across multiple Page Objects. This allows for easy code sharing across the Page Objects without
copy/pasting code.

Components can be written in context of a non-list or list. Non-list Components can be considered "static" in that only
one of its kind exists on a web page. For example, a header is a great example of this. The header will always be a singular
defined set of properties, and you'll never see 4 or 5 of them on the same web page. List Components can be considered "dynamic" in that any
amount of them might be present on the web page. For example, a search results page is a great example of this. A search results page will
show a number of results. Each result contains the same title, price, and add to cart button. These elements
can be written as a Component, and then built as a `List` from the Page Object.

## Component Example - Non-list Context
The same rules apply to Page Objects in how Components are intended to be agnostic to the web driver and navigation methods. Component classes define
a subset of properties from a website that can appear across any amount of Page Objects. For example, perhaps the same header might exist across
multiple pages. That header might have a large collection of properties, such as a search bar, search button, links, etc. Taking our Page Object
example above, we could update the header property to be its own component, referencing the parent in the `By` locators.

**The Component**
```java
@Getter
public class HeaderComponent extends BaseComponent {
  
    private final ImageWebElement pageCompanyIcon = new ImageWebElement(By.cssSelector("#header #company_icon"));
    private final InputWebElement searchInput = InputWebElement(By.cssSelector("#header #search_input"));
    private final ButtonWebElement searchButton = ButtonWebElement(By.cssSelector("#header #search_button"));
    private final LinkWebElement loginLink = LinkWebElement(By.cssSelector("#header #login"));
    private final LinkWebElement registerLink = LinkWebElement(By.cssSelector("#header #register"));
    
    // Helper functions for clicking links and returning Page Objects. E.G, clickRegisterLink() may return a RegisterPage Page Object.
}
```

**The Updated Page Object**
```java
@Getter
public class LoginPage extends BasePage {

    private final HeaderComponent headerComponent = new HeaderComponent();
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
        clickLoginButton();
    }
}
```

Let's break down what we see in the example above.

1. `BaseComponent` Extension on `HeaderComponent`
    1. All Components should inherit functionality from the `BaseComponent` class. Similar to Page Objects, there are some extremely helpful methods there that allow the same access as mentioned from Page Objects, but also the construction of Component lists. That's covered later.
2. Properties (or aka Java Fields)
    1. Exactly the same fundamentals as the Page Object. These fields are the elements that exist on the page. With a "non-list" component, we always want to provide the fully qualified `By` locator that includes the parent.
3. Getters
    1. Standard getter functions are auto generated with the lombok `@Getter` annotation from the class level. Any time we want to interact with an element, we
       always call the getter instead of the raw field.
4. No Constructor
    1. Here, we don't need to customize our constructor with a verification method. The Page Object is responsible for this.
5. Helper Functions
    1. We can include any additional helper functions here to make our calling code easier to read.
6. Instantiating the Component on the Page Object
    1. The Page Object can then instantiate a new instance of the Component we just wrote. Code calling the loginPage will now have access to getHeaderComponent()'s getters and helper functions!

##### Component Example - List Context
Building a Component under a "list" context is fundamentally the same. In the search results page example mentioned from the Components section, we
can define properties exactly the same way. The only difference is that a list Component doesn't define properties with the parent in the `By` locator.
The search results page would then create the list of the search results with `BaseComponent`'s `buildComponentList()` method.

**The Component**
```java
@Getter
public class SearchResultItem extends BaseComponent {

   private final DivWebElement itemName = new DivWebElement(".inventory_item_name");
   private final DivWebElement itemDescription = new DivWebElement(".inventory_item_desc");
   private final DivWebElement itemPrice = new DivWebElement(".inventory_item_price");
   private final ButtonWebElement addToCart = new ButtonWebElement(".btn_primary");

   public void clickAddToCartButton() {
      getAddToCart().click();
   }
}
```

**The Page Object**
```java
@Getter
public class SearchResultsPage extends BasePage {
   private final static String INVENTORY_ITEM_SELECTOR = ".inventory_item";
   private final HeaderComponent headerComponent = new HeaderComponent();
   private final DivWebElement inventoryListContainer = new DivWebElement(By.cssSelector(".inventory_list"));
   private final DropDownWebElement sortDropDown = new DropDownWebElement(".product_sort_container");

   public SearchResultsPage() {
      verifyIsOnPage(getInventoryList());
   }

   public List<SearchResultItem> getSearchResultsList() {
      var listOfElements = getInventoryListContainer().findElements(DivWebElement.class, INVENTORY_ITEM_SELECTOR);
      return buildComponentList(listOfElements, SearchResultItem.class);
   }
}
```

Let's break down what we see in the example above.

1. `BaseComponent` Extension on `SearchResultItem`
    1. All Components should inherit functionality from the `BaseComponent` class. Similar to Page Objects, there are some extremely helpful methods there that allow access the same access as mentioned from Page Objects, but also the construction of Component lists, which is what we are doing now!
2. Properties (or aka Java Fields)
    1. Exactly the same fundamentals as the Page Object. These fields are the elements that exist on the page. With a "list" component, we only want to provide the `By` locator that is considered the "child" of the parent. In this case, the inventory name's `By` locator would be `.inventory_item_name`.
3. Getters
    1. Standard getter functions are auto generated with the lombok `@Getter` annotation from the class level. Any time we want to interact with an element, we
       always call the getter instead of the raw field.
4. No Constructor
    1. Here, we don't need to customize our constructor with a verification method. The Page Object is responsible for this.
5. Helper Functions
    1. We can include any additional helper functions here to make our calling code easier to read.
6. Creating the List
    1. The `SearchResultsPage` handles the creation of the list of Components by invoking the `buildComponentList()` method. It requires a list of the search result elements to iterate through, along with the class of the Component you are building, in order to correctly map each result to a new instance of the Component. Calling code can then interact with the list of results as their own Objects, e.g. `getSearchResultsList().get(0).getItemName()`. It also makes streaming on the list of results much easier, as you can map or filter based on the objects themselves.

## Component List Best Practices
As mentioned in the [Page Objects](#page-objects) and [Page Objects Best Practices](#page-object-best-practices) section, it's best to instantiate new
strongly typed elements at the class level with a "new" keyword. The same applies to the usage of Components when building lists. Do not invoke `findElements()`
when creating a list at the class level. Instead, create a new public method that builds the list of elements, and maps them to the component, per the example above.

**An example of incorrect usage:**
```java
@Getter
public class SearchResultsPage extends BasePage {
  private final HeaderComponent headerComponent = new HeaderComponent();
  private final DropDownWebElement sortDropDown = new DropDownWebElement(".product_sort_container");
  private final List<DivWebElement> inventoryList = new DivWebElement(By.cssSelector(".inventory_list").findElements(DivWebElement.class, ".inventory_item"));
  private final List<SearchResultItem> inventoryItems = buildComponentList(getInventoryList(), SearchResultItem.class);

  public SearchResultsPage() {
    verifyIsOnPage(getInventoryList());
  }
}
```

This mapping only works if the list of elements from the parent container, provided as the parameter "listOfElements", match the exact element items from the DOM. I.E, if there
is a difference between the size of "listOfElements" and the size of the elements contained under the DOM's parent, the nth-child mapping will not function as expected.
Take the DOM from the SauceLabs demo site, for example, on the cart page:

```html
<div class="cart_list">
  <div class="cart_quantity_label">QTY</div>
  <div class="cart_desc_label">DESCRIPTION</div>
  <div class="cart_item">
    <div class="cart_quantity">1</div>
    <div class="cart_item_label">
      <a href="#" id="item_4_title_link">
        <div class="inventory_item_name">Sauce Labs Backpack</div>
      </a><div class="inventory_item_desc">some great description.</div>
      <div class="item_pricebar">
        <div class="inventory_item_price">$29.99</div>
        <button class="btn btn_secondary btn_small cart_button" data-test="remove-sauce-labs-backpack" id="remove-sauce-labs-backpack" name="remove-sauce-labs-backpack">Remove</button>
      </div>
    </div>
  </div>
  <div class="cart_item">....</div>
</div>
```

The parent is ".cart list" and the child elements we wish to map as a component list are ".cart_item". In this case, findElements() for ".cart_list .cart_item" will give us a size
2 list, but the DOM has a size 4 list, counting the ".cart_quantity_label," ".cart_desc_label," and the two elements we care about. The ideal scenario for building
a list of components is when the sizes of the lists match between the found elements and the DOM, i.e. if all the child elements were ONLY ".cart_item." But, there is a way to
circumvent THIS specific scenario by specifying an index correction to this method. With this example above, we can pass an index correction of 2, since there are 2 elements above
the first element we care about, and the css selector nth-child locators will map correctly.

This isn't the most ideal usage, and is meant only for situations where there are nth elements before the first element we care about. This method is not (yet) dynamic enough
to perform mapping on situations that are more complicated, such as random element locations all throughout the DOM along with the list of elements we care about.

An alternative to building component lists is to find all elements, stream through them to select a specific index, and then perform a findElement() for the specific child locator
you'd like to interact with. This alternative is useful when you're dealing with singular elements on a page that have a difficult design structure. For example, let's say we're
interacting with a sidebar that contains filter radio buttons.

With this filter example, let's say we've created an Enum that contains String values for the possible filter title's we'd like to select. On the page object for the search results
page, we could write the following function:

```java
public class SomePageObject {
  
  public void selectCategoryFilterByEnum(CategoryFilterEnum filterChoice) {
    var categoryList = new DivWebElement(".sidebar_container").findElements(DivWebElement.class, ".filters_container");
    var categoryLink = categoryList
        .stream()
        .filter(result -> {
          var resultTitle = result.getText();
          return resultTitle.contains(filterChoice.getCategory());
        })
        .reduce((a,b) -> {
          throw new ExceptionOfYourChoice(String.format(
              "Multiple Elements found [%s] and [%s]", a, b));
        });
    if (categoryLink.isPresent()) {
      categoryLink.get().click();
    } else {
      throw new ExceptionOfYourChoice(String.format(
          "Could not find a selection with choice [%s]", filterChoice.getCategoryAsString()));
    }
  } 
}
```

This above example illustrates that sometimes building a component list of all the things isn't always the best option and is just another tool in the toolbox. With the above example,
we've limited the interaction to a singular method on a page object and cut down on our overall code.