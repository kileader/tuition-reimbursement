
#Author: Kevin Leader

Feature: Employee navigates through the website

  Scenario: Employee wants to be logged in on index page
    Given Employee is on index page
    And Employee is logged out on index page
    When Employee enters a good email on index page
    And Employee enters a good password on index page
    And Employee clicks the Log In button on index page
    Then Employee is logged in on index page

	Scenario Outline: Employee uses navbar links
    Given Employee is on index page
	  When Employee clicks on <link name> in the navbar
	  Then The browser navigates to <page name> by navbar
	  Examples: 
	    | link name							  								| page name    |
	    |	Tuition Reimbursement Management System | index page	 |
	    | Request a Reimbursement 								| request page |
	    | Update Your Requests	  								| update page  |
	    | Review Others Requests  								|	review page  |
	  
	Scenario Outline: Employee uses homepage links in the main
    Given Employee is on index page
		When Employee clicks on <link name> in main
		Then The browser navigates to <page name> by main
	 	Examples:
	 		| link name							  								| page name    |
	    | Request a Reimbursement									| request page |
	    | Update Your Requests	  								| update page  |
	    | Review Others Requests  								|	review page  |
