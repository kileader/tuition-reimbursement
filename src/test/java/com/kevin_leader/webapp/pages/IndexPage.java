package com.kevin_leader.webapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
	
	public WebDriver driver;
	
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
	
	public IndexPage(WebDriver driver) {
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
	
	public void clickRequestMainLink() {
		requestMainLink.click();
	}
	
	public void clickUpdateMainLink() {
		updateMainLink.click();
	}
	
	public void clickReviewMainLink() {
		reviewMainLink.click();
	}
	
}
