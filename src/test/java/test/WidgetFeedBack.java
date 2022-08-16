package test;


import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.PageDemoPisano;
import utilities.*;

import java.util.Random;

public class WidgetFeedBack extends TestBaseCross {
    PageDemoPisano pisano=new PageDemoPisano(); //we are using pom model for clean coding 
    Actions action=new Actions(Driver.getDriver()); //we will use this object in other test methods
    Faker fake=new Faker();//we will use some required blanks for text box
    JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.getDriver();
    SoftAssert soft=new SoftAssert();// we are using soft assert for executing our test even failed during codes
   @Test (priority = 1)
    public void setUp() {
        //user navigate to "https://demo.pisano.co/widget_test.html?code=PSN-cg56fpn&env=stage" url
        Driver.getDriver().get(ConfigReader.getProperty("pisanoUrl"));
        //user click on the widget 
        //ps. as automation processes we have to switch to iframe
       jsExecutor.executeScript("arguments[0].scrollIntoView(true);", pisano.firstFrame); pisano.firstFrame.click();
        //user have to leave from first iframe for switch second iframe
       Driver.getDriver().switchTo().defaultContent();
    }

    @Test (priority = 2)
    public void firstStage() {
       //user have to switch second frame
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", pisano.secondFrame);
        //pisano.secondFrame.click();
        Driver.getDriver().switchTo().frame(pisano.secondFrame);
        //user give recommendation point for friends or collegues
           //it will select randomly
        Random rnd=new Random();
        int boundForRandom=pisano.recommendation.size();
        int random= rnd.nextInt(boundForRandom);
        //recommendation webElement will return as list
        pisano.recommendation.get(random).click();
        //clicking sentiment
        int sentimentBound=pisano.sentiment.size();
        int sentimntRandom=rnd.nextInt(sentimentBound);
        pisano.sentiment.get(sentimntRandom).click();
        //verify to whether nextpagebutton is visible or not
        soft.assertTrue(pisano.nextPage.isDisplayed());
        //nextpage btton must be clickable
        pisano.nextPage.click();
    }

    @Test(priority = 3)
    public void secondStage() {
        //verify conervsation flow text box if visible or not
        soft.assertTrue(pisano.converFlow.isDisplayed());
        //using action class make action with keyboard with nonlocated
        action.click(pisano.converFlow)
                .sendKeys(fake.leagueOfLegends().quote()).perform();
        //click agreement radioButton
        pisano.acceptAgreement.click();
        //click on pisanoCheckBox
        pisano.pisanoCheckBox.click();
        //click nextpage
        pisano.secondNextPage.click();

    }

    @Test (priority = 4 )
    public void lastStage() {
        //user verify if mail box whether visible or not then we wiil use action and faker classfor fiilng the blanks
        soft.assertTrue(pisano.emailTextBox.isDisplayed());
        //action class
        action.click(pisano.emailTextBox).sendKeys(fake.internet().emailAddress(),Keys.TAB,fake.name().firstName()
                        ,Keys.TAB,"50773684732",Keys.TAB)
                .perform();
        //wait for 3 seconds
        ReusableMethods.waitFor(5);
        //click submit
        ReusableMethods.waitForClickablility(pisano.submit,5);
       // jsExecutor.executeScript("arguments[0].scrollIntoView(true);", pisano.submit);
        pisano.submit.click();
        //verify expected word whether the same with actual or not
        ReusableMethods.waitFor(2);
        String expectedWord="Thanks, I think test were succeeded";
        String actualWord=pisano.actualText.getText();
        soft.assertTrue(expectedWord.equalsIgnoreCase(actualWord));
        //take webelement screenshot (i created this methods for weblement and all pages)
        WebElement widgetConteiner= pisano.widgetConteiner;
        ScreenShot.takeShootWebElement(widgetConteiner);
    }
}

