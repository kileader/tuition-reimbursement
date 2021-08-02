package com.kevin_leader.webapp.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.kevin_leader.webapp.pages.IndexPage;
import com.kevin_leader.webapp.runners.TrmsRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class IndexStepsImpl {
	
	public static IndexPage indexPage = TrmsRunner.indexPage;
	public static WebDriver driver = TrmsRunner.driver;
	public static Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(10, TimeUnit.SECONDS)
            .pollingEvery(250, TimeUnit.MILLISECONDS)
            .ignoring(Exception.class);
	
	@Given("^Employee sees login inputs on index page$")
	public void employee_sees_login_inputs_on_index_page() {
	    wait.until(ExpectedConditions.visibilityOf(indexPage.loginContainer));
	    assertNotNull(indexPage.pageContainer.getAttribute("hidden"));
	    assertNull(indexPage.loginContainer.getAttribute("hidden"));
	}

	@When("^Employee enters a good email on index page$")
	public void employee_enters_a_good_email_on_index_page() {
	    indexPage.sendKeysToEmailInput("wmarousek9@springer.com");
	}

	@When("^Employee enters a good password on index page$")
	public void employee_enters_a_good_password_on_index_page() {
	    indexPage.sendKeysToPasswordInput("ZWcVaZOXi");
	}

	@When("^Employee clicks the Log In button on index page$")
	public void employee_clicks_the_Log_In_button_on_index_page() {
	    indexPage.clickLogInButton();
	}

	@Then("^Employee is logged in on index page$")
	public void employee_is_logged_in_on_index_page() {
	    wait.until(ExpectedConditions.visibilityOf(indexPage.pageContainer));
	    assertNull(indexPage.pageContainer.getAttribute("hidden"));
        assertNotNull(indexPage.loginContainer.getAttribute("hidden"));
	}
	
	@Given("^Employee is on index page$")
	public void employee_is_on_index_page() {
		driver.get("file:///C:/Users/Kevin/Documents/STS/tuition-reimbursement/src/main/pages/index.html");
	}
	
	@When("Employee clicks on Tuition Reimbursement Management System in the navbar$")
	public void employee_clicks_on_Tuition_Reimbursement_Management_System_in_the_navbar() {
		indexPage.clickIndexNavLink();
	}
	
	@Then("^The browser navigates to index page by navbar$")
	public void the_browser_navigates_to_index_page_by_navbar() {
		assertEquals("Tuition Reimbursement Management System", driver.getTitle());
	}
	
	@When("^Employee clicks on Request a Reimbursement in the navbar$")
	public void employee_clicks_on_Request_a_Reimbursement_in_the_navbar() {
		indexPage.clickRequestNavLink();
	}
	
	@Then("^The browser navigates to request page by navbar$")
	public void the_browser_navigates_to_request_page_by_navbar() {
		assertEquals("Request a Reimbursement", driver.getTitle());
	}
	
	@When("^Employee clicks on Update Your Requests in the navbar$")
	public void employee_clicks_on_Update_Your_Requests_in_the_navbar() {
		indexPage.clickUpdateNavLink();
	}
	
	@Then("^The browser navigates to update page by navbar$")
	public void the_browser_navigates_to_update_page_by_navbar() {
		assertEquals("Update Your Requests", driver.getTitle());
	}
	
	@When("^Employee clicks on Review Others Requests in the navbar$")
	public void employee_clicks_on_Review_Others_Requests_in_the_navbar() {
		indexPage.clickReviewNavLink();
	}
	
	@Then("^The browser navigates to review page by navbar$")
	public void the_browser_navigates_to_review_page_by_navbar() {
		assertEquals("Review Others Requests", driver.getTitle());
	}
	
	@When("^Employee clicks on Request a Reimbursement in main$")
	public void employee_clicks_on_Request_a_Reimbursement_in_main() {
		indexPage.clickRequestMainLink();
	}
	
	@Then("^The browser navigates to request page by main$")
	public void the_browser_navigates_to_request_page_by_main() {
		assertEquals("Request a Reimbursement", driver.getTitle());
	}
	
	@When("^Employee clicks on Update Your Requests in main$")
	public void employee_clicks_on_Update_Your_Requests_in_main() {
		indexPage.clickUpdateMainLink();
	}
	
	@Then("^The browser navigates to update page by main$")
	public void the_browser_navigates_to_update_page_by_main() {
		assertEquals("Update Your Requests", driver.getTitle());
	}
	
	@When("^Employee clicks on Review Others Requests in main$")
	public void employee_clicks_on_Review_Others_Requests_in_main() {
		indexPage.clickReviewMainLink();
	}
	
	@Then("^The browser navigates to review page by main$")
	public void the_browser_navigates_to_review_page_by_main() {
		assertEquals("Review Others Requests", driver.getTitle());
	}
}
