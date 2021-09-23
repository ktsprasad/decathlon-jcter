/****************************************************************************
 Author: Srinivas Prasad K T
 Last updated: 09/24/2021
 Description: Initialize the driver with browser using Singleton and
              run specific tag before and after each scenario
 ***************************************************************************/

package steps;

import base.DriverHandler;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.IOException;

public class Hook{

    private WebDriver driver;
    public Scenario scenario;

    public WebDriver getDriver(){
        return DriverHandler.GetInstanceDriverHandler().getDriver();
    }

    @Before
    public void InitiliazeTest(Scenario scenario){
        System.out.println("Running Scenario: " + scenario.getName());

        //Reporter.assignAuthor("QA - Srinivas Prasad K T");

        // Here Singleton pattern is being used, to avoid opening a new browser each time a scenario runs
        this.scenario = scenario;
        driver = DriverHandler.GetInstanceDriverHandler().getDriver();
    }

    //this will run after each scenario
    @After
    public void TearDownTest(Scenario scenario){

        //Add screenshot enable the below if block if screenshot requires only for failed case
        //if(scenario.isFailed()){
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            try {
                //This takes a screenshot from the driver at save it to the specified location
                File sourcePath = ((TakesScreenshot) DriverHandler
                        .GetInstanceDriverHandler().getDriver()).getScreenshotAs(OutputType.FILE);

                //Creates the destination path
                File destinationPath = new File(System.getProperty("user.dir") + "/report/screenshots/" + screenshotName + ".png");

                //Copy taken screenshot from source location to destination location
                Files.copy(sourcePath, destinationPath);

                //Attach the specified screenshot to the test
                Reporter.addScreenCaptureFromPath(destinationPath.toString());
            } catch (IOException e) {
            	System.out.println("oops something went wrong " +e);
            }
       // }
    }
    
    @AfterSuite
    public void tearDownAll() {
    	DriverHandler.GetInstanceDriverHandler().TearDown();
    }
}
