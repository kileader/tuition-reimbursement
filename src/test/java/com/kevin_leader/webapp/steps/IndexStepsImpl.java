package com.kevin_leader.webapp.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kevin_leader.webapp.pages.IndexPage;
import com.kevin_leader.webapp.runners.TrmsRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class IndexStepsImpl {

    public static IndexPage indexPage = TrmsRunner.indexPage;
    public static WebDriver driver = TrmsRunner.driver;
    public static WebDriverWait wait = new WebDriverWait(driver, 30);

    @Given("^Employee is on index page$")
    public void employee_is_on_index_page() {
        driver.get("file:///C:/Users/Kevin/Documents/STS/"
                + "tuition-reimbursement/src/main/pages/index.html");
    }

    @Given("^Employee is logged out on index page$")
    public void employee_is_logged_out_on_index_page() {
        if (indexPage.logoutLink.isDisplayed()) {
            indexPage.logoutLink.click();
        }
        wait.until(ExpectedConditions.visibilityOf(indexPage.loginContainer));
    }

    @When("^Employee enters a good email on index page$")
    public void employee_enters_a_good_email_on_index_page() {
        indexPage.emailInput.sendKeys("wmarousek9@springer.com");
    }

    @When("^Employee enters a good password on index page$")
    public void employee_enters_a_good_password_on_index_page() {
        indexPage.passwordInput.sendKeys("ZWcVaZOXi");
    }

    @When("^Employee clicks the Log In button on index page$")
    public void employee_clicks_the_Log_In_button_on_index_page() {
        indexPage.loginButton.click();
    }

    @Then("^Employee is logged in on index page$")
    public void employee_is_logged_in_on_index_page() {
        wait.until(ExpectedConditions.visibilityOf(indexPage.pageContainer));
        assertNull(indexPage.pageContainer.getAttribute("hidden"));
        assertNotNull(indexPage.loginContainer.getAttribute("hidden"));
    }

    @When("^Employee clicks on Tuition Reimbursement Management System in the navbar$")
    public void employee_clicks_on_Tuition_Reimbursement_Management_System_in_the_navbar() {
        indexPage.indexNavLink.click();
    }

    @Then("^The browser navigates to index page by navbar$")
    public void the_browser_navigates_to_index_page_by_navbar() {
        assertEquals("Tuition Reimbursement Management System",
                driver.getTitle());
    }

    @When("^Employee clicks on Request a Reimbursement in the navbar$")
    public void employee_clicks_on_Request_a_Reimbursement_in_the_navbar() {
        indexPage.requestNavLink.click();
    }

    @Then("^The browser navigates to request page by navbar$")
    public void the_browser_navigates_to_request_page_by_navbar() {
        assertEquals("Request a Reimbursement", driver.getTitle());
    }

    @When("^Employee clicks on Update Your Requests in the navbar$")
    public void employee_clicks_on_Update_Your_Requests_in_the_navbar() {
        indexPage.updateNavLink.click();
    }

    @Then("^The browser navigates to update page by navbar$")
    public void the_browser_navigates_to_update_page_by_navbar() {
        assertEquals("Update Your Requests", driver.getTitle());
    }

    @When("^Employee clicks on Review Others Requests in the navbar$")
    public void employee_clicks_on_Review_Others_Requests_in_the_navbar() {
        indexPage.reviewNavLink.click();
    }

    @Then("^The browser navigates to review page by navbar$")
    public void the_browser_navigates_to_review_page_by_navbar() {
        assertEquals("Review Others Requests", driver.getTitle());
    }

    @When("^Employee clicks on Request a Reimbursement in main$")
    public void employee_clicks_on_Request_a_Reimbursement_in_main() {
        indexPage.requestMainLink.click();
    }

    @Then("^The browser navigates to request page by main$")
    public void the_browser_navigates_to_request_page_by_main() {
        assertEquals("Request a Reimbursement", driver.getTitle());
    }

    @When("^Employee clicks on Update Your Requests in main$")
    public void employee_clicks_on_Update_Your_Requests_in_main() {
        indexPage.updateMainLink.click();
    }

    @Then("^The browser navigates to update page by main$")
    public void the_browser_navigates_to_update_page_by_main() {
        assertEquals("Update Your Requests", driver.getTitle());
    }

    @When("^Employee clicks on Review Others Requests in main$")
    public void employee_clicks_on_Review_Others_Requests_in_main() {
        indexPage.reviewMainLink.click();
    }

    @Then("^The browser navigates to review page by main$")
    public void the_browser_navigates_to_review_page_by_main() {
        assertEquals("Review Others Requests", driver.getTitle());
    }
}
