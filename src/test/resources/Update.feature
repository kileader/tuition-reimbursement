
#Author: Kevin Leader

Feature: Employee wants to update their request
  
  Scenario: Employee wants to be logged in on update page
    Given Employee is on update page
    And Employee is logged out on update page
    When Employee enters a good email on update page
    And Employee enters a good password on update page
    And Employee clicks the Log In button on update page
    Then Employee is logged in on update page
  
  Scenario: Employee wants to see their request
    Given Choose the Request becomes enabled
    When Employee clicks on Choose the Request dropdown
    And Employee chooses the request just approved
    Then Employee sees the request information
  
  Scenario: Employee wants to add an attachment
    When Employee fills out attachment fields
    And Employee clicks on Add Attachment
    Then Processing popup shows for Add Attachment
    And Success popup shows for Add Attachment
    And Employee is back on index page after Add Attachment
  
  Scenario: Employee wants to add a grade
    Given Employee is on update page
    And Choose the Request becomes enabled
    When Employee clicks on Choose the Request dropdown
    And Employee chooses the request just approved
    Then Employee sees the request information
    When Employee fills out grade field
    And Employee clicks on Add Grade
    Then Processing popup shows for Add Attachment
    And Success popup shows for Add Attachment
    And Employee is back on index page after Add Attachment
    
  Scenario: Employee wants to see their attachment and grade
    Given Employee is on update page
    And Choose the Request becomes enabled
    When Employee clicks on Choose the Request dropdown
    And Employee chooses the request just approved
    Then Employee sees the request information
    And Employee sees new attachment and grade
