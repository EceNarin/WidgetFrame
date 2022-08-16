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
    @FindBy(xpath = "//*[@id=\"pisano-frame-root\"]/div/div/div[2]/svg/g/path[1]")
    public WebElement widget;
    @FindBy(xpath = "//*[@id=\"3bc04ad8-f4ef-4b19-a62e-efc43f3cb237\"]/div")
    public List<WebElement> recommendation;
    @FindBy(xpath = "//*[@id=\"6d01c7ef-f98b-4108-983b-2fc78743ab6f\"]/div/span/img")
    public List<WebElement> sentiment;
    @FindBy(xpath = "//*[@id=\"a0b6bac7-d894-42c7-a677-31814456ead9\"]")
    public WebElement nextPage;
    @FindBy(xpath = "//input[@type=\"text\"]")
    public WebElement converFlow;
    @FindBy(xpath = "//input[@type=\"text\"]")
    public WebElement emailTextBox;
    @FindBy(xpath = "//div[@class=\"label\"]//div")
    public WebElement actualText;
    @FindBy(xpath = "//div[@class=\"widget-content\"]")
    public WebElement widgetConteiner;
}
