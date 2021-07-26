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
	
	public RequestPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickIndexNavLink() {
		indexNavLink.click();
	}
	
	public void clickRequestNavLink() {
		requestNavLink.click();
	}
	
	public void clickUpdateNavLink() {
		updateNavLink.click();
	}
	
	public void clickReviewNavLink() {
		reviewNavLink.click();
	}
	
	public void clickChooseEvent() {
		chooseEventButton.click();
	}
	
	public void clickCreateEvent() {
		createEventButton.click();
	}
	
	public void clickChooseFormat() {
		chooseFormatButton.click();
	}
	
	public void clickCreateFormat() {
		createFormatButton.click();
	}
}
