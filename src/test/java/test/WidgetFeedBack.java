package test;


import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.PageDemoPisano;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.ScreenShot;

import java.util.Random;

public class WidgetFeedBack {
    PageDemoPisano pisano=new PageDemoPisano(); //we are using pom model for clean coding 
    Actions action=new Actions(Driver.getDriver()); //we will use this object in other test methods
    Faker fake=new Faker();//we will use some required blanks for text box
    SoftAssert soft=new SoftAssert();// we are using soft assert for executing our test even failed during codes
   @Test (priority = 1)
    public void setUp() {
        //user navigate to "https://demo.pisano.co/widget_test.html?code=PSN-cg56fpn&env=stage" url
        Driver.getDriver().get(ConfigReader.getProperty("pisanoUrl"));
        //user click on the widget 
        //ps. as automation processes we have to switch to iframe 
        WebElement iframe= pisano.firstFrame;
        Driver.getDriver().switchTo().frame(iframe);
        //verifying before click if webelement is "dislayed"
       soft.assertTrue(pisano.widget.isDisplayed());
        //after this verfied we can sure whether webelement is enable 
        pisano.widget.click();
    }

    @Test (priority = 2)
    public void firstStage() {
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
        //usinf action class make action with keyboard with nonlocated
        action.click(pisano.converFlow)
                .sendKeys(fake.lorem().sentence(1), Keys.TAB).click()
                .sendKeys(Keys.TAB).click().sendKeys(Keys.TAB).click().perform();
    }

    @Test (priority = 4 )
    public void lastStage() {
        //user verify if mail box whether visible or not then we wiil use action and faker classfor fiilng the blanks
        soft.assertTrue(pisano.emailTextBox.isDisplayed());
        //action class
        action.click(pisano.emailTextBox).sendKeys(fake.internet().emailAddress(),Keys.TAB,fake.name().firstName(),Keys.TAB,fake.phoneNumber().phoneNumber(),Keys.TAB)
                .click()
                .perform();
        //verify expected word whether the same with actual or not
        String expectedWord="Thanks, I think test were succeeded";
        String actualWord=pisano.actualText.getText();
        soft.assertTrue(expectedWord.equalsIgnoreCase(actualWord));
        //take webelement screenshot (i created this methods for weblement and all pages)
        WebElement widgetConteiner= pisano.widgetConteiner;
        ScreenShot.takeShootWebElement(widgetConteiner);
    }
}

