package com.kevin_leader.webapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

public class IndexPage {
	
	public WebDriver driver;
	
	public static Wait<WebDriver> wait;
	
	@FindBy(xpath = "//a[@class='navbar-brand']")
	public WebElement indexNavLink;
	
	@FindBy(xpath = "//ul[@class='navbar-nav']/li[2]/a")
	public WebElement requestNavLink;
	
	@FindBy(xpath = "//ul[@class='navbar-nav']/li[3]/a")
	public WebElement updateNavLink;
	
	@FindBy(xpath = "//ul[@class='navbar-nav']/li[4]/a")
	public WebElement reviewNavLink;
	
	@FindBy(xpath = "//article/p[2]/a")
	public WebElement requestMainLink;
	
	@FindBy(xpath = "//article/p[3]/a")
	public WebElement updateMainLink;
	
	@FindBy(xpath = "//article/p[4]/a")
	public WebElement reviewMainLink;
	
	@FindBy(id = "email")
	public WebElement emailInput;
	
	@FindBy(id = "password")
	public WebElement passwordInput;
	
	@FindBy(id = "login-button")
	public WebElement loginButton;
	
	@FindBy(id = "login-container")
	public WebElement loginContainer;
	
	@FindBy(id = "page-container")
	public WebElement pageContainer;
	
	public IndexPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickIndexNavLink() {
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
	
	public void clickRequestMainLink() {
	    try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		requestMainLink.click();
	}
	
	public void clickUpdateMainLink() {
	    try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		updateMainLink.click();
	}
	
	public void clickReviewMainLink() {
	    try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reviewMainLink.click();
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
        passwordInput.sendKeys(keys);
    }
    
    public void clickLogInButton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginButton.click();
    }
	
}
