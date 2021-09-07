package com.kevin_leader.webapp.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kevin_leader.webapp.pages.UpdatePage;
import com.kevin_leader.webapp.runners.TrmsRunner;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateStepsImpl {

    public static UpdatePage updatePage = TrmsRunner.updatePage;
    public static WebDriver driver = TrmsRunner.driver;
    public static WebDriverWait wait = new WebDriverWait(driver, 60);

    @Given("^Employee is on update page$")
    public void employee_is_on_update_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^Employee is logged out on update page$")
    public void employee_is_logged_out_on_update_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee enters a good email on update page$")
    public void employee_enters_a_good_email_on_update_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee enters a good password on update page$")
    public void employee_enters_a_good_password_on_update_page()
            throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee clicks the Log In button on update page$")
    public void employee_clicks_the_Log_In_button_on_update_page()
            throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Employee is logged in on update page$")
    public void employee_is_logged_in_on_update_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^Choose the Request becomes enabled$")
    public void choose_the_Request_becomes_enabled() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee clicks on Choose the Request dropdown$")
    public void employee_clicks_on_Choose_the_Request_dropdown()
            throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee chooses the request just approved$")
    public void employee_chooses_the_request_just_approved() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Employee sees the request information$")
    public void employee_sees_the_request_information() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee fills out attachment fields$")
    public void employee_fills_out_attachment_fields() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee clicks on Add Attachment$")
    public void employee_clicks_on_Add_Attachment() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Processing popup shows for Add Attachment$")
    public void processing_popup_shows_for_Add_Attachment() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Success popup shows for Add Attachment$")
    public void success_popup_shows_for_Add_Attachment() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Employee is back on index page after Add Attachment$")
    public void employee_is_back_on_index_page_after_Add_Attachment()
            throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee fills out grade field$")
    public void employee_fills_out_grade_field() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Employee clicks on Add Grade$")
    public void employee_clicks_on_Add_Grade() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Employee sees new attachment and grade$")
    public void employee_sees_new_attachment_and_grade() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
