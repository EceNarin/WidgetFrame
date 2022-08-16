package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class PageDemoPisano {
    public PageDemoPisano() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//iframe[@id=\"psn-widget-button-frame\"]")
    public WebElement firstFrame;
    @FindBy(xpath = "//iframe[@id=\"psn-widget-content-frame\"]")
    public WebElement secondFrame;
    @FindBy(xpath = "(//div[@class=\"button__check is-radio\"])[1]")
    public WebElement acceptAgreement;
    @FindBy(xpath = "(//div[@class=\"button__check is-checkbox\"])[1]")
    public WebElement pisanoCheckBox;
    @FindBy(xpath = "//*[@id=\"3bc04ad8-f4ef-4b19-a62e-efc43f3cb237\"]/div")
    public List<WebElement> recommendation;
    @FindBy(xpath = "//*[@id=\"6d01c7ef-f98b-4108-983b-2fc78743ab6f\"]/div/span/img")
    public List<WebElement> sentiment;
    @FindBy(xpath = "//*[@id=\"a0b6bac7-d894-42c7-a677-31814456ead9\"]")
    public WebElement nextPage;

    @FindBy(xpath = "//div[@id=\"37521850-fa76-4599-a027-096228733e79\"]")
    public WebElement secondNextPage;
    @FindBy(xpath = "//input[@id=\"78c3febe-85a2-4c11-8b94-f92d15ce6387\"]")
    public WebElement converFlow;

    @FindBy(xpath = "//input[@id=\"82c5b3f1-20d3-49f7-9611-9e01859868b5\"]")
    public WebElement emailTextBox;
    @FindBy(xpath = "//div[@class=\"label\"]//div")
    public WebElement actualText;
    @FindBy(xpath = "//div[@class=\"widget-content\"]")
    public WebElement widgetConteiner;
    @FindBy(xpath = "//div[@id=\"d7c25d6a-17e8-45d3-a831-67732c8b81e2\"]")
    public WebElement submit;
}
