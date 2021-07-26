package com.kevin_leader.webapp.steps;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import com.kevin_leader.webapp.pages.IndexPage;
import com.kevin_leader.webapp.runners.TrmsRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NavigationStepsImpl {
	
	public static IndexPage indexPage = TrmsRunner.indexPage;
	public static WebDriver driver = TrmsRunner.driver;
	
	@Given("^Employee is on index page$")
	public void employee_is_on_index_page() {
		driver.get("file:///C:/Users/Kevin/Documents/workspace-spring-tool-suite-4-4.10.0."
				+ "RELEASE/tuition-reimbursement/src/main/pages/index.html");
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
