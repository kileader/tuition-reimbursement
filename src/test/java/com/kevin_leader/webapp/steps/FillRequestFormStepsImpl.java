package com.kevin_leader.webapp.steps;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.openqa.selenium.WebDriver;

import com.kevin_leader.webapp.pages.RequestPage;
import com.kevin_leader.webapp.runners.TrmsRunner;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FillRequestFormStepsImpl {
	
	public static RequestPage requestPage = TrmsRunner.requestPage;
	public static WebDriver driver = TrmsRunner.driver;
	
	@Given("^Employee is on request page$")
	public void employee_is_on_index_page() {
		driver.get("file:///C:/Users/Kevin/Documents/workspace-spring-tool-suite-4-4.10.0"
				+ ".RELEASE/tuition-reimbursement/src/main/pages/request.html");
	}
	
	@When("Employee clicks Choose from Events button$")
	public void employee_clicks_choose_from_events_button() {
		requestPage.clickChooseEvent();
	}
	
	@Then("^Choose Your Event dropdown is seen on page$")
	public void choose_your_event_dropdown_is_seen_on_page() {
		assertNull(requestPage.chooseEventDiv.getAttribute("hidden"));
	}
	
	@And("^New Event fields are hidden from page$")
	public void new_event_fields_are_hidden_from_page() {
		assertNotNull(requestPage.createEventDiv.getAttribute("hidden"));
	}
	
	@When("^Employee clicks Create a New Event button$")
	public void employee_clicks_create_a_new_event_button() {
		requestPage.clickCreateEvent();
	}
	
	@Then("^New Event fields are seen on page$")
	public void new_event_fields_are_seen_on_page() {
		assertNull(requestPage.createEventDiv.getAttribute("hidden"));
	}
	
	@And("^Choose Your Event dropdown is hidden from page$")
	public void choose_your_event_dropdown_is_hidden_from_page() {
		assertNotNull(requestPage.chooseEventDiv.getAttribute("hidden"));
	}

	@Given("^Create a New Event button has been clicked$")
	public void create_a_new_event_button_has_been_clicked() {
		if (requestPage.createEventDiv.getAttribute("hidden") != null) {
			requestPage.clickCreateEvent();
		}
	}
	
	@When("^Employee clicks Choose from Grading Formats button$")
	public void employee_clicks_choose_from_grading_formats_button() {
		requestPage.clickChooseFormat();
	}
	
	@Then("^Choose the Grading Format dropdown is seen on page$")
	public void choose_the_grading_format_dropdown_is_seen_on_page() {
		assertNull(requestPage.chooseFormatDiv.getAttribute("hidden"));
	}
	
	@And("^New Grading Format fields are hidden from page$")
	public void new_grading_format_fields_are_hidden_from_page() {
		assertNotNull(requestPage.createFormatDiv.getAttribute("hidden"));
	}
	
	@When("^Employee clicks Create a New Grading Format button$")
	public void employee_clicks_create_a_new_grading_format_button() {
		requestPage.clickCreateFormat();
	}
	
	@Then("^New Grading Format fields are seen on page$")
	public void new_grading_format_fields_are_seen_on_page() {
		assertNull(requestPage.createFormatDiv.getAttribute("hidden"));
	}
	
	@And("^Choose the Grading Format dropdown is hidden from page$")
	public void choose_the_grading_format_dropdown_is_hidden_from_page() {
		assertNotNull(requestPage.chooseFormatDiv.getAttribute("hidden"));
	}
	
}
