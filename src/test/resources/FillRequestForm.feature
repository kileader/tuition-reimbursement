
#Author: Kevin Leader

Feature: Employee fills out a reimbursement request form
	
	Background:
		Given Employee is on request page
		
	Scenario: Revealing Choose Your Event and hiding New Event fields works
		When Employee clicks Choose from Events button
		Then Choose Your Event dropdown is seen on page
		And New Event fields are hidden from page
	
	Scenario: Revealing New Event fields and hiding Choose Your Event works
		When Employee clicks Create a New Event button
		Then New Event fields are seen on page
		And Choose Your Event dropdown is hidden from page
	
	Scenario: Revealing Choose the Grading Format and 
			hiding New Grading Format fields works
		Given Create a New Event button has been clicked
		When Employee clicks Choose from Grading Formats button
		Then Choose the Grading Format dropdown is seen on page
		And New Grading Format fields are hidden from page
		
	Scenario: Revealing New Grading Format fields and
			hiding Choose the Grading Format works
		Given Create a New Event button has been clicked
		When Employee clicks Create a New Grading Format button
		Then New Grading Format fields are seen on page
		And Choose the Grading Format dropdown is hidden from page