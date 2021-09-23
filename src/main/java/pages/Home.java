/****************************************************************************
 Author: Srinivas Prasad K T
 Last updated: 09/24/2021
 Description: Page object to handle locators and methods for Home page
 ***************************************************************************/

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

public class Home {

	private WebDriver driver;

	public Home(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	// Link to accept cookies
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Accepter")
	private WebElement linkCookies;

	// search box
	@FindBy(how = How.ID, using = "search")
	private WebElement txtSearch;

	// location pop up box
	@FindBy(how = How.CLASS_NAME, using = "btn-close")
	private WebElement btnPopup;

	// Suggestion for search box
	@FindBy(how = How.CSS, using = ".search_suggestions strong")
	private WebElement lblSuggestion;

	// div with suggestion
	@FindBy(how = How.CSS, using = (".search_suggestions"))
	private WebElement divSuggestion;

	// menu item
	@FindBy(how = How.XPATH, using = "//nav[1]/ul[@id='aut-header-menu']/li[1]/a[1]")
	private WebElement lblmenu;

	// submenu item
	@FindBy(how = How.XPATH, using = "//li[@class='menu nav-orange show-for-large-up mega-menu-item active']//li[.='Idées cadeaux fête des Mères']")
	private WebElement lblsubmenu;

	public void CheckHomePage() throws InterruptedException {
		By locator = By.partialLinkText("Accepter");

		Boolean isPresent = Utils.existsElement(driver, locator);
		if (isPresent) {
			ExpectedCondition e = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return (linkCookies.getSize().height > 11);
				}
			};
			WebDriverWait wait = (new WebDriverWait(driver, 5));
			wait.until(e);
			linkCookies.click();
		}
	}

	public void setEnterText(String searchcriteria) {
		txtSearch.sendKeys(searchcriteria);
	}

	public void clickonSearchBox() {
		{
			ExpectedCondition e = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return (txtSearch.isDisplayed());
				}
			};
			WebDriverWait wait = (new WebDriverWait(driver, 10));
			wait.until(e);
			txtSearch.click();
		}
	}
	
	public void clickonFirstAutoSearchBox() {
		{
			ExpectedCondition e = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return (lblSuggestion.isDisplayed());
				}
			};
			WebDriverWait wait = (new WebDriverWait(driver, 10));
			wait.until(e);
			lblSuggestion.click();
		}
	}

	public void closeLocationPopup() {
		{
			ExpectedCondition e = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return (btnPopup.isDisplayed());
				}
			};
			WebDriverWait wait = (new WebDriverWait(driver, 10));
			wait.until(e);
			btnPopup.click();
		}
	}

	public String getSuggestions() {
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(ExpectedConditions.elementToBeClickable(divSuggestion));

		return lblSuggestion.getText();
	}

	public void LookForSubitem() {
		Actions action = new Actions(driver);
		action.moveToElement(lblmenu).clickAndHold(lblmenu).perform();

		WebDriverWait wait = (new WebDriverWait(driver, 10));
		wait.until(ExpectedConditions.elementToBeClickable(lblsubmenu));

		lblsubmenu.click();

	}

}
