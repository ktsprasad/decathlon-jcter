/****************************************************************************
 Author: Srinivas Prasad K T
 Last updated: 09/24/2021
 Description: Step definitions for handling home page
 ***************************************************************************/


package steps;

import base.DriverHandler;
import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.cucumber.listener.Reporter;
import pages.Home;
import java.text.Normalizer;
import java.util.List;

import io.cucumber.datatable.DataTable;

public class MainPage {

    private Home homepage;
    private WebDriver driver;

    //using dependency injection for Hook
    public MainPage(Hook hook) {
        this.driver = hook.getDriver();
        homepage = new Home(driver);
    }

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() throws Throwable {
        driver.navigate().to("https://www.decathlon.in");
        //Check the first time for cookies overlay
        homepage.CheckHomePage();
    }
    
    @Given("^Close the location pop up$")
    public void closeLocationPopUp() throws Throwable {
    	homepage.closeLocationPopup();
    }

    @When("^I look for specific product \"([^\"]*)\" in search box$")
    public void iLookForSpecificProductInSearchBox(String searchcriteria) throws Throwable {
        homepage.setEnterText(searchcriteria);
    }
    
//    @When("^I look for specific product in search box$")
//    public void iLookForSpecificProductInSearchBox(DataTable productCollection) throws Throwable {
//    	List<List<String>> productList = productCollection.asList(String.class);
//    	for (List<String> product : productList) {
//        homepage.setEnterText(product.toString());
//    	}
//    }
    
    @And("^Click on search Box$")
    public void clickOnAnyBoxProduct() throws Throwable {
    	homepage.clickonSearchBox();
    }
    
    
    @Then("^I should see suggestion for my search \"([^\"]*)\"$")
    public void iShouldSeeSuggestionForMySearch(String searchcriteria) throws Throwable {
        // normalizer transforms Unicode text into an equivalent composed or decomposed form, allowing for easier
        // sorting and searching of text.
        //Assert.assertEquals(Normalizer.normalize(homepage.getSuggestions().substring(0,11),Normalizer.Form.NFD)
        //        , Normalizer.normalize("glove", Normalizer.Form.NFD));
    	Assert.assertEquals(homepage.getSuggestions(), searchcriteria);
    	Reporter.addStepLog("first suggestions contains search text : " +searchcriteria);
    }
    
    @And("^I should able to select first auto suggestion product$")
    public void iShouldAbleToSelectFirstAutoSuggestionProduct() throws Throwable {
    	homepage.clickonFirstAutoSearchBox();
    }

    @Given("^I look for an specific product in the menu$")
    public void iLookForAnSpecificProductInTheMenu() throws Throwable {
        homepage.LookForSubitem();
    }

}