#Author: narmatharamshankar@gmail.com
     
 Feature: SwaggerPetStore Api Regression Testing Scenarios
      
  ## POST - ADD A NEW PET TO THE PETSTORE  
      Scenario Outline: POST - ADD A NEW PET TO THE PETSTORE - Verify a pet is successfully created with valid data 
      Given I have a pet named "<name>" with status "<status>"
      When  I try to add the pet to the petstore
      Then  The response status code is returned as <statuscode>
      And   The pet is successfully added to the petstore
         
        Examples: 
      | name  |  status    | statuscode |
      | Tiggy |  available |  200       |
      | Bunny |  pending   |  200       |
       
      Scenario Outline: POST - ADD A NEW PET TO THE PETSTORE - Verify creating a pet with invalid data fails with status code 405(ADD A NEW PET TO THE PETSTORE)
      Given I create  pet with invalid jsondata 
      Then  The response status code is returned as 405
   
      
     Scenario Outline: POST - UPLOAD AN IMAGE - Verify a  url for the pet can be added successfully to the petstore a response status code 200 is returned
    
     Given I have a pet named "<name>" with status "<status>"
     When  I try to add the pet to the petstore
     Then  The response status code is returned as <create_status_code>
     And   The pet is successfully added to the petstore
     When  I try to update the image for the pet 
     Then  The response status code is returned as <update_image_statuscode>
      Examples: 
      | name  |  status    | create_status_code | update_image_statuscode | 
      | Parrot|  available |  200               | 200                     |    
  
  ## PUT - UPDATE AN EXISTING PET - PET OBJECT NEEDS TO BE ADDED    
     
     Scenario Outline: PUT - UPDATE AN EXISTING PET - PET OBJECT NEEDS TO BE ADDED -  Verify the pet status can be updated successfully for a valid pet data and a response status code 200 is returned
     
     Given I have a pet named "<name>" with status "<status>"
     When  I try to add the pet to the petstore
     Then  The response status code is returned as <create_status_code>
     And   The pet is successfully added to the petstore
     When  I try to update the status of the pet to "<update_status>"
     Then  The response status code is returned as <update_status_statuscode>
     And  The status is updated for the pet
     Examples: 
     | name  |  status    | create_status_code | update_status| update_status_statuscode | 
     | Lucy  |  available |  200               | sold         | 200                      |
   
    ## GET - FIND PETS BY STATUS 
   
    Scenario Outline: GET - FIND PETS BY STATUS - Verify the pet details are successfully retrieved by status and  a response status code 200 is returned 
    Given I search for pets with status "<validstatus>"
    Then  The response status code is returned as <statuscode>
    And the response has all the pets with status "<validstatus>"

    Examples: 
      | validstatus                   | statuscode |
      | available                     |  200       |
      | sold                          |  200       |
      | pending                       |  200       |
      
   Scenario: GET - FIND PETS BY STATUS - Verify the searching the pets with wrong status fails and returns error code 400 invalid status value 
    Given I search for pets with status "unavailable"
    Then  The response status code is returned as 400
    
     ## GET - FIND PET BY ID    
   Scenario Outline: GET - FIND PET BY ID -  Verify the search for the pet with valid id is successful and a response status code 200 is returned
      Given I have a pet named "<name>" with status "<status>"
      When  I try to add the pet to the petstore
      Then  The response status code is returned as <create_statuscode>
      And   The pet is successfully added to the petstore
      When  The pet is searched with id
      Then  The response status code is returned as <Find_status_code>
        Examples: 
      | name  |  status    | create_statuscode | Find_status_code |  
      | Kitty |  available |  200              | 200              |
  
    Scenario Outline: GET - FIND PET BY ID - Verify the search for already deleted pet fails and returns a status code 404 Pet Not Found
   Given I have a pet named "<name>" with status "<status>"
   When  I try to add the pet to the petstore
   Then  The response status code is returned as <create_statuscode>
   And   The pet is successfully added to the petstore
   When  I try to delete the pet 
   Then  The response status code is returned as <delete_status_code> 
   When  The pet is searched with id
   Then  The response status code is returned as <Find_status_code>
      Examples: 
      | name   |   status     | create_statuscode | delete_status_code |  Find_status_code |
      | Tweety |  available   |  200              | 200                |  404              |
        
  
   Scenario: GET - FIND PET BY ID - Verify the search for the pet with invalid id fails with status code 400 Invalid ID Supplied
   Given I try to find a pet with invalid id "abc"
   Then The response status code is returned as 400
   
    ## POST - UPDATE A PET IN THE STORE WITH FORM DATA 
 
 Scenario Outline: POST - UPDATE A PET IN THE STORE WITH FORM DATA - Verify  update status of the existing pet is successful and a response status code 200 is returned
   Given I have a pet named "<name>" with status "<status>"
   When  I try to add the pet to the petstore
   Then  The response status code is returned as <create_statuscode>
   And   The pet is successfully added to the petstore
   When  I try to update the pet status code in form data to "<update_status>"
   Then  The response status code is returned as <statusupdate_status_code> 
  Examples: 
      | name   |   status     | create_statuscode | update_status |  statusupdate_status_code |
      | COW    |  available   |  200              | sold          |  200                      | 
      
  Scenario Outline: POST - UPDATE A PET IN THE STORE WITH FORM DATA - Verify update status of the existing pet falis when an invalid status is given  and a response status code 405 invalid input is returned
            
            
   Given I have a pet named "<name>" with status "<status>"
   When  I try to add the pet to the petstore
   Then  The response status code is returned as <create_statuscode>
   And   The pet is successfully added to the petstore
   When  I try to update the pet status code in form data to "<update_status>"
   Then  The response status code is returned as <statusupdate_status_code> 
            
   Examples: 
      | name   |   status     | create_statuscode | update_status |  statusupdate_status_code |
      | MEWMEW |  available   |  200              | unavailable   |  405                      | 
            
   ## DELETE - DELETE A PET 
   Scenario Outline: DELETE A PET - Verify an exisiting pet is successfully deleted and a response status code 200 is returned
   Given I have a pet named "<name>" with status "<status>"
   When  I try to add the pet to the petstore
   Then  The response status code is returned as <create_statuscode>
   And   The pet is successfully added to the petstore
   When  I try to delete the pet 
   Then  The response status code is returned as <delete_status_code> 
      Examples: 
      | name  |  status    | create_statuscode | delete_status_code |  
      | Doggy |  available |  200              | 200                |
      
 
  Scenario Outline: DELETE A PET -  Verify attempt to delete a pet that is already deleted falis with error code 404 pet not found  
   Given I have a pet named "<name>" with status "<status>"
   When  I try to add the pet to the petstore
   Then  The response status code is returned as <create_statuscode>
   And   The pet is successfully added to the petstore
   When  I try to delete the pet 
   Then  The response status code is returned as <delete1_status_code> 
   And   I try to delete the pet 
   Then  The response status code is returned as <delete2_status_code>
      Examples: 
      | name  |  status    | create_statuscode | delete1_status_code |  delete2_status_code|
      | Doggy |  available |  200              | 200                 |  404                |
      
  Scenario: DELETE A PET - Verify an attempt to delete a pet with invalid id fails with error code 400 invalid id supplied
   Given I try to delete a pet with invalid id "invalid_id"
   Then  The response status code is returned as 400
    