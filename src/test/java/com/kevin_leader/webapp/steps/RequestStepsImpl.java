package com.kevin_leader.webapp.steps;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kevin_leader.webapp.pages.RequestPage;
import com.kevin_leader.webapp.runners.TrmsRunner;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RequestStepsImpl {

    public static RequestPage requestPage = TrmsRunner.requestPage;
    public static WebDriver driver = TrmsRunner.driver;
    public static WebDriverWait wait = new WebDriverWait(driver, 60);

    @Given("^Employee is on request page$")
    public void employee_is_on_request_page() {
        driver.get("file:///C:/Users/Kevin/Documents/STS/"
                + "tuition-reimbursement/src/main/pages/request.html");
    }

    @Given("^Employee is logged out on request page$")
    public void employee_is_logged_out_on_request_page() {
        if (requestPage.logoutLink.isDisplayed()) {
            requestPage.logoutLink.click();
        }
        wait.until(ExpectedConditions.visibilityOf(requestPage.loginContainer));
    }

    @When("^Employee enters a good email on request page$")
    public void employee_enters_a_good_email_on_request_page() {
        requestPage.emailInput.sendKeys("wmarousek9@springer.com");
    }

    @When("^Employee enters a good password on request page$")
    public void employee_enters_a_good_password_on_request_page() {
        requestPage.passwordInput.sendKeys("ZWcVaZOXi");
    }

    @When("^Employee clicks the Log In button on request page$")
    public void employee_clicks_the_Log_In_button_on_request_page() {
        requestPage.loginButton.click();
    }

    @Then("^Employee is logged in on request page$")
    public void employee_is_logged_in_on_request_page() {
        wait.until(ExpectedConditions.visibilityOf(requestPage.requestNavLink));
        requestPage.requestNavLink.click();
        wait.until(ExpectedConditions.visibilityOf(requestPage.pageContainer));
    }

    @When("^Employee clicks Choose from Events button$")
    public void employee_clicks_choose_from_events_button() {
        wait.until(
                ExpectedConditions.visibilityOf(requestPage.chooseEventButton));
        requestPage.chooseEventButton.click();
    }

    @Then("^Choose Your Event dropdown is seen on page$")
    public void choose_your_event_dropdown_is_seen_on_page() {
        assertNull(requestPage.chooseEventDiv.getAttribute("hidden"));
    }

    @Then("^New Event fields are hidden from page$")
    public void new_event_fields_are_hidden_from_page() {
        assertNotNull(requestPage.createEventDiv.getAttribute("hidden"));
    }

    @When("^Employee clicks Create a New Event button$")
    public void employee_clicks_create_a_new_event_button() {
        wait.until(
                ExpectedConditions.visibilityOf(requestPage.createEventButton));
        requestPage.createEventButton.click();
    }

    @Then("^New Event fields are seen on page$")
    public void new_event_fields_are_seen_on_page() {
        assertNull(requestPage.createEventDiv.getAttribute("hidden"));
    }

    @Then("^Choose Your Event dropdown is hidden from page$")
    public void choose_your_event_dropdown_is_hidden_from_page() {
        assertNotNull(requestPage.chooseEventDiv.getAttribute("hidden"));
    }

    @When("^Employee fills all event fields to location$")
    public void employee_fills_all_event_fields_to_location() {
        requestPage.eventNameInput.sendKeys("How to Run in Place");
        requestPage.tuitionInput.sendKeys("700.25");
        requestPage.startDateInput.sendKeys("10112021");
        requestPage.startTimeInput.sendKeys("1030A");
        requestPage.endDateInput.sendKeys("10152021");
        requestPage.endTimeInput.sendKeys("0700P");
        requestPage.locationInput.sendKeys("At Home");
    }

    @When("^Employee clicks on event type dropdown$")
    public void event_type_is_able_to_be_chosen() {
        wait.until(ExpectedConditions
                .elementToBeClickable(requestPage.eventTypeSelect));
        requestPage.eventTypeSelect.click();
    }

    @When("^Employee chooses an event type$")
    public void employee_chooses_an_event_type() {
        Select eventTypes = new Select(requestPage.eventTypeSelect);
        eventTypes.selectByValue("1");
    }

    @When("^Employee clicks Choose from Grading Formats button$")
    public void employee_clicks_choose_from_grading_formats_button() {
        wait.until(ExpectedConditions
                .visibilityOf(requestPage.chooseFormatButton));
        requestPage.chooseFormatButton.click();
    }

    @Then("^Choose the Grading Format dropdown is seen on page$")
    public void choose_the_grading_format_dropdown_is_seen_on_page() {
        assertNull(requestPage.chooseFormatDiv.getAttribute("hidden"));
    }

    @Then("^New Grading Format fields are hidden from page$")
    public void new_grading_format_fields_are_hidden_from_page() {
        assertNotNull(requestPage.createFormatDiv.getAttribute("hidden"));
    }

    @When("^Employee clicks Create a New Grading Format button$")
    public void employee_clicks_create_a_new_grading_format_button() {
        wait.until(ExpectedConditions
                .visibilityOf(requestPage.createFormatButton));
        requestPage.createFormatButton.click();
    }

    @Then("^New Grading Format fields are seen on page$")
    public void new_grading_format_fields_are_seen_on_page() {
        assertNull(requestPage.createFormatDiv.getAttribute("hidden"));
    }

    @Then("^Choose the Grading Format dropdown is hidden from page$")
    public void choose_the_grading_format_dropdown_is_hidden_from_page() {
        assertNotNull(requestPage.chooseFormatDiv.getAttribute("hidden"));
    }
    
    @When("^Employee fills all grading format fields$")
    public void employee_fills_all_grading_format_fields() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee fills reimbursement details fields$")
    public void employee_fills_reimbursement_details_fields() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee clicks Submit Form$")
    public void employee_clicks_Submit_Form() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Processing popup shows for Submit Form$")
    public void processing_popup_shows_for_Submit_Form() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Success popup shows for Submit Form$")
    public void success_popup_shows_for_Submit_Form() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Employee is back on index page after Submit Form$")
    public void employee_is_back_on_index_page_after_Submit_Form()
            throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee clicks on update link for request$")
    public void employee_clicks_on_update_link_for_request() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Employee is on update page for request$")
    public void employee_is_on_update_page_for_request() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Choose the Request becomes enabled for request$")
    public void choose_the_Request_becomes_enabled_for_request()
            throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee clicks on Choose the Request dropdown for request$")
    public void employee_clicks_on_Choose_the_Request_dropdown_for_request()
            throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee chooses the request just made$")
    public void employee_chooses_the_request_just_made() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Employee sees the request information for request$")
    public void employee_sees_the_request_information_for_request()
            throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
