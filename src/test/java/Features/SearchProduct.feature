Feature: Search product and Place the order

 Scenario: Search experience for product search in both Home and Offer page
    
    Given user is on Greenkart landing page 
    When User searched with shortname "Tom" and extract name of the product
    Then user search for "Tom" shortname in offer page 
    And Validate same product name machtes with landing page 

