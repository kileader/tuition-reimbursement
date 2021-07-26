
#Author: Kevin Leader

Feature: Employee navigates through the website
	
	Background:
		Given Employee is on index page
	
	Scenario Outline: Employee uses navbar links
	  When Employee clicks on <link name> in the navbar
	  Then The browser navigates to <page name> by navbar
	
	  Examples: 
	    | link name							  								| page name    |
	    |	Tuition Reimbursement Management System | index page	 |
	    | Request a Reimbursement 								| request page |
	    | Update Your Requests	  								| update page  |
	    | Review Others Requests  								|	review page  |
	  
	Scenario Outline: Employee uses homepage links in the main
		When Employee clicks on <link name> in main
		Then The browser navigates to <page name> by main
	 	
	 	Examples:
	 		| link name							  								| page name    |
	    | Request a Reimbursement									| request page |
	    | Update Your Requests	  								| update page  |
	    | Review Others Requests  								|	review page  |
