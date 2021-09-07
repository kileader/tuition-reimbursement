package com.kevin_leader.webapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestPage {

    public WebDriver driver;

    @FindBy(xpath = "//a[@class='navbar-brand']")
    public WebElement indexNavLink;

    @FindBy(xpath = "//ul[@class='navbar-nav']/li[2]/a")
    public WebElement requestNavLink;

    @FindBy(xpath = "//ul[@class='navbar-nav']/li[3]/a")
    public WebElement updateNavLink;

    @FindBy(xpath = "//ul[@class='navbar-nav']/li[4]/a")
    public WebElement reviewNavLink;

    @FindBy(xpath = "//ul[@class='navbar-nav']/li[5]/a")
    public WebElement logoutLink;

    @FindBy(xpath = "//button[@id='choose-event']")
    public WebElement chooseEventButton;

    @FindBy(id = "create-event")
    public WebElement createEventButton;

    @FindBy(id = "choose-format")
    public WebElement chooseFormatButton;

    @FindBy(id = "create-format")
    public WebElement createFormatButton;

    @FindBy(id = "choose-from-events")
    public WebElement chooseEventDiv;

    @FindBy(id = "create-new-event")
    public WebElement createEventDiv;

    @FindBy(id = "choose-from-formats")
    public WebElement chooseFormatDiv;

    @FindBy(id = "create-new-format")
    public WebElement createFormatDiv;
    
    @FindBy(id = "event-name")
    public WebElement eventNameInput;
    
    @FindBy(id = "tuition")
    public WebElement tuitionInput;
    
    @FindBy(id = "start-date")
    public WebElement startDateInput;
    
    @FindBy(id = "start-time")
    public WebElement startTimeInput;
    
    @FindBy(id = "end-date")
    public WebElement endDateInput;
    
    @FindBy(id = "end-time")
    public WebElement endTimeInput;
    
    @FindBy(id = "location")
    public WebElement locationInput;
    
    @FindBy(id = "event-types-loading")
    public WebElement eventTypesLoading;
    
    @FindBy(id = "event-type")
    public WebElement eventTypeSelect;
    
    @FindBy(id = "format-name")
    public WebElement formatNameInput;

    @FindBy(id = "email")
    public WebElement emailInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(id = "page-container")
    public WebElement pageContainer;

    @FindBy(id = "login-container")
    public WebElement loginContainer;

    public RequestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
