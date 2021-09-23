/****************************************************************************
 Author: Srinivas Prasad K T
 Last updated: 09/24/2021
 Description: Step definitions for handling sub category page
 ***************************************************************************/

package steps;

import base.DriverHandler;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.cucumber.listener.Reporter;

import pages.SubCategory;

public class SubCategoryPage {

	private WebDriver driver;

	private SubCategory subCategory;

	// using dependency injection for Hook
	public SubCategoryPage(Hook hook) {
		this.driver = hook.getDriver();
		subCategory = new SubCategory(driver);
	}

	@Then("^I select first card from the list$")
	public void iSelectFirstCardFromTheList() throws Throwable {
		subCategory.ClickonFirstBox();
	}

	@And("^I should be able to select size \"([^\"]*)\"$")
	public void iShouldBeAbleToSelectSize(String size) throws Throwable {
		subCategory.setSizeProduct(size);
	}

	@Then("^As a customer able select option add to cart$")
	public void asACustomerAbleSelectOptionAddToCart() throws Throwable {
		subCategory.addToCart();
	}

	@Then("^I should be able to perform go to cart$")
	public void iShouldBeAbleToPerformGoToCart() throws Throwable {
		subCategory.goToCart();
	}

	@And("^I should be able to see the order summary (\\d+) and (\\d+)$")
	public void iShouldBeAbleToSeeTheOrderSummary(int prize, int quantity) throws Throwable {
		boolean isPageDisplayed = subCategory.isOrderSummaryPageDispayed();
		Assert.assertEquals(true, isPageDisplayed, "Order summary page not displayed");
		String productPrize = subCategory.getProductPrize();
		String totalQty = subCategory.getProductQuantity();
		String estDeliveryChages = subCategory.getProductEstCharges();
		String grandTotal = subCategory.getProductGrandTotal();
		int grandTotalPrize = Integer.parseInt(grandTotal.split("₹ ")[1]);
		int grandTotalCalculation = Integer.parseInt(estDeliveryChages.split("₹ ")[1])
				+ Integer.parseInt(productPrize.split("₹ ")[1]);
		Reporter.addStepLog("productPrize " + Integer.parseInt(productPrize.split("₹ ")[1]));
		Reporter.addStepLog("totalQty " + Integer.parseInt(totalQty));
		Reporter.addStepLog("estDeliveryChages " + Integer.parseInt(estDeliveryChages.split("₹ ")[1]));
		Reporter.addStepLog("grandTotal " + Integer.parseInt(grandTotal.split("₹ ")[1]));
		Reporter.addStepLog("grandTotalPrize " +grandTotalPrize);
		Reporter.addStepLog("grandTotalCalculation " +grandTotalCalculation);
		Assert.assertEquals(prize, Integer.parseInt(productPrize.split("₹ ")[1]), "Product prize is not matching");
		Assert.assertEquals(quantity, Integer.parseInt(totalQty), "Total quantity not matching");
		Assert.assertEquals(grandTotalPrize, grandTotalCalculation, "Grand total not matching");
	}

	@Then("^As a customer able to perform proceed to checkout$")
	public void asACustomerAbleToPerformProceedToCheckout() throws Throwable {
		subCategory.proceesToCheckout();
	}
	
	@Then("^I should be able to see login page with text \"([^\"]*)\"$")
	public void iShouldBeAbleToSeeLoginPageWithText(String loginText) throws Throwable {
		String isLoginTextDisplayed = subCategory.isLoginPopupDispayed();
		Reporter.addStepLog("Is login text displayed " + isLoginTextDisplayed);
		Assert.assertEquals(isLoginTextDisplayed, loginText, "Login text not displayed");
	}

	@Then("^I select the select your size drop down$")
	public void iSelectTheSelectYourSizeDropDown() throws Throwable {
		subCategory.setSizeDropDown();
	}

	@When("^Subcategory page product page appears$")
	public void subcategoryPageProductPageAppears() throws Throwable {
		Assert.assertEquals(subCategory.CheckSubCategoryPage().substring(0, 20), "Week-end en amoureux",
				"Product page does not load");
	}

	@Then("^I should see the filter options$")
	public void iShouldSeeTheFilterOptions() throws Throwable {
		Assert.assertEquals(subCategory.CheckFilter(), "FILTRES", "Filter options are not appearing");
	}

	@And("^I search on the box filter for Title \"([^\"]*)\"$")
	public void iSearchOnTheBoxFilterForTitle(String title) throws Throwable {
		subCategory.setTxtSearch(title);
	}

	@And("^Click on any Box$")
	public void clickOnAnyBoxProduct() throws Throwable {
		subCategory.ClickonBox();
	}
}
