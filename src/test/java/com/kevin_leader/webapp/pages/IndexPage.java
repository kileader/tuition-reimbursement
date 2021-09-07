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

    @FindBy(xpath = "//ul[@class='navbar-nav']/li[5]/a")
    public WebElement logoutLink;

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

}
