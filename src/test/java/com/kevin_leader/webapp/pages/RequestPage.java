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
	
	@FindBy(id = "choose-event")
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
	
	public void clickIndexNavLink() throws InterruptedException {
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    indexNavLink.click();
	}
	
	public void clickRequestNavLink() {
	    try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		requestNavLink.click();
	}
	
	public void clickUpdateNavLink() {
	    try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		updateNavLink.click();
	}
	
	public void clickReviewNavLink() {
	    try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		reviewNavLink.click();
	}
	
	public void clickChooseEvent() {
	    try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		chooseEventButton.click();
	}
	
	public void clickCreateEvent() {
	    try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		createEventButton.click();
	}
	
	public void clickChooseFormat() {
	    try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		chooseFormatButton.click();
	}
	
	public void clickCreateFormat() {
	    try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		createFormatButton.click();
	}
	
	public void clickLogInButton() {
	    try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginButton.click();
    }

	public void sendKeysToEmailInput(String keys) {
	    try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        emailInput.sendKeys(keys);
    }

    public void sendKeysToPasswordInput(String keys) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        emailInput.sendKeys(keys);
    }
}
