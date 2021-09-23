/****************************************************************************
 Author: Srinivas Prasad K T
 Last updated: 09/24/2021
 Description: Page object to handle locators and methods for Sub Category page
 ***************************************************************************/

package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubCategory {

	private WebDriver driver;

	public SubCategory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;

	}

	// label for subcategory header
	@FindBy(how = How.XPATH, using = "//div[5]/section/h1")
	private WebElement lblHeader;

	// laberl for filter section
	@FindBy(how = How.XPATH, using = "//div[1]/header/h2")
	private WebElement lblFilter;

	// textbox to search in filter
	@FindBy(how = How.ID, using = "location")
	private WebElement txtSearch;

	// order summary page
	@FindBy(how = How.ID, using = "order-summary-block")
	private WebElement orderSummary;
	
	// Login pop up 
	@FindBy(how = How.CSS, using = ".decaFadeInRight h4")
	private WebElement loginPopup;

	// order quantity
	@FindBy(how = How.CSS, using = ".align-items-center input")
	private WebElement orderQty;

	// product prize
	@FindBy(how = How.CSS, using = ".align-items-center h4")
	private WebElement productPrize;

	// estimated delivery charges
	@FindBy(how = How.CSS, using = "#order-summary-block div:nth-child(2) p:nth-child(2)")
	private WebElement estPrize;

	// Total charges of products
	@FindBy(how = How.CSS, using = "#order-summary-block div:nth-child(3)")
	private WebElement totalPrize;

	// Total charges of products
	@FindBy(how = How.CSS, using = ".btn_ptc.btn-yellow-gradient.btn-md.btn-stretched.btn")
	private WebElement proceedToCheckout;

	// div containing a subcategory product
	@FindBy(how = How.XPATH, using = "//section[@id='ac-cloudSearchResults']//article[1]//a[1]")
	private WebElement divSubCategoryBox;

	// div containing a subcategory product
	@FindBy(how = How.CSS, using = ".btn-yellow-gradient.btn-sm.btn")
	private WebElement addToCart;

	// div containing a subcategory product
	@FindBy(how = How.CSS, using = ".btn-brand-blue.btn-md.btn-stretched.btn")
	private WebElement goToCart;

	// div containing a subcategory product
	@FindBy(how = How.CSS, using = ".react-select")
	private WebElement divDropDown;

	// div containing a subcategory product
	@FindBy(how = How.CSS, using = (".ais-Hits ul li a"))
	private WebElement divFirstProductBox;

	public String CheckSubCategoryPage() throws InterruptedException {
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(ExpectedConditions.visibilityOf(lblHeader));
		return lblHeader.getText();
	}

	public String CheckFilter() throws InterruptedException {
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(ExpectedConditions.visibilityOf(lblFilter));
		return lblFilter.getText();
	}
	
	public String getProductPrize() throws InterruptedException {
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(ExpectedConditions.visibilityOf(productPrize));
		return productPrize.getText();
	}
	
	public String getProductQuantity() throws InterruptedException {
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(ExpectedConditions.visibilityOf(orderQty));
		return orderQty.getAttribute("value");
	}
	
	public String getProductEstCharges() throws InterruptedException {
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(ExpectedConditions.visibilityOf(estPrize));
		return estPrize.getText();
	}
	
	public String getProductGrandTotal() throws InterruptedException {
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(ExpectedConditions.visibilityOf(totalPrize));
		return totalPrize.getText();
	}

	public void setTxtSearch(String title) {
		txtSearch.sendKeys(title);
		txtSearch.sendKeys(Keys.RETURN);
	}

	public boolean isOrderSummaryPageDispayed() {
		return orderSummary.isDisplayed();
	}
	
	public String isLoginPopupDispayed() {
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(ExpectedConditions.visibilityOf(totalPrize));
		return loginPopup.getText();
	}

	public void setSizeProduct(String size) {
		Actions keyDown = new Actions(driver);
		switch (size) {
		case "S":
			keyDown.sendKeys(Keys.chord(Keys.ENTER)).perform();
			break;
		case "M":
			keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.ENTER)).perform();
			break;
		case "L":
			keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.ENTER)).perform();
			break;
		case "XL":
			keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.ENTER)).perform();
			break;
		case "2XL":
			keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.ENTER)).perform();
			break;
		default:
			System.out.println("No Selection performed");
		}
		try {
	        Thread.sleep(3*1000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

	public void setSizeDropDown() {
		Actions actions = new Actions(driver);
		System.out.println("divDropDown.isDisplayed()" + divDropDown.isDisplayed());
		int getX = divDropDown.getLocation().getX();
		System.out.println("X coordinate: " + getX);
		int getY = divDropDown.getLocation().getY();
		System.out.println("Y coordinate: " + getY);
		actions.moveByOffset(getX + 1, getY + 1).click();
		actions.build().perform();
		System.out.println("Clicked successfully on size dropdowm");
	}

	public void addToCart() {
		ExpectedCondition e = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (addToCart.isDisplayed());
			}
		};
		WebDriverWait wait = (new WebDriverWait(driver, 15));
		wait.until(e);
		addToCart.click();
	}

	public void goToCart() {
		ExpectedCondition e = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (goToCart.isDisplayed());
			}
		};
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(e);
		goToCart.click();
	}
	
	public void proceesToCheckout() {
		ExpectedCondition e = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (proceedToCheckout.isDisplayed());
			}
		};
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(e);
		proceedToCheckout.click();
	}

	public void ClickonBox() {
		ExpectedCondition e = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (divSubCategoryBox.isDisplayed());
			}
		};
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(e);
		divSubCategoryBox.click();
	}

	public void ClickonFirstBox() {
		ExpectedCondition e = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (divFirstProductBox.isDisplayed());
			}
		};
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(e);
		divFirstProductBox.click();
	}

}
