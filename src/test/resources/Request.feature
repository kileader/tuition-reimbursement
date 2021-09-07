
#Author: Kevin Leader

Feature: Employee fills out a reimbursement request form
	
  Scenario: Employee wants to be logged in on request page
    Given Employee is on request page
    And Employee is logged out on request page
    When Employee enters a good email on request page
    And Employee enters a good password on request page
    And Employee clicks the Log In button on request page
    Then Employee is logged in on request page

	Scenario: Revealing Choose Your Event and hiding New Event fields works
		When Employee clicks Choose from Events button
		Then Choose Your Event dropdown is seen on page
		And New Event fields are hidden from page
	
	Scenario: Revealing New Event fields and hiding Choose Your Event works
		When Employee clicks Create a New Event button
		Then New Event fields are seen on page
		And Choose Your Event dropdown is hidden from page
		
	Scenario: Employee fills event fields
    When Employee fills all event fields to location
    And Employee clicks on event type dropdown
    And Employee chooses an event type
	
	Scenario: Revealing Choose the Grading Format and 
			hiding New Grading Format fields works
		When Employee clicks Choose from Grading Formats button
		Then Choose the Grading Format dropdown is seen on page
		And New Grading Format fields are hidden from page
		
	Scenario: Revealing New Grading Format fields and
			hiding Choose the Grading Format works
		When Employee clicks Create a New Grading Format button
		Then New Grading Format fields are seen on page
		And Choose the Grading Format dropdown is hidden from page
	
	Scenario: Employee finishes filling fields and submits
    When Employee fills all grading format fields
    And Employee fills reimbursement details fields
    And Employee clicks Submit Form
    Then Processing popup shows for Submit Form
    And Success popup shows for Submit Form
    And Employee is back on index page after Submit Form
  
  Scenario: Employee wants to check on request
    When Employee clicks on update link for request
    Then Employee is on update page for request
    And Choose the Request becomes enabled for request
    When Employee clicks on Choose the Request dropdown for request
    And Employee chooses the request just made
    Then Employee sees the request information for request
    