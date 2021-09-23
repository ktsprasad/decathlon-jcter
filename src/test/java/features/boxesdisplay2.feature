#**************************************************************************
#Author: Srinivas Prasad K T
#Last updated: 01/23/21
#Description: Feature file for search product and add to cart user story
#**************************************************************************
Feature: Search product and add to cart
  As a customer I want to view a list of search products so I can select some to purchase

  Background: 
    Given I am on the home page

  @smoke
  Scenario: As a Customer I search in the main field
    Given Close the location pop up
    When I look for specific product "glove" in search box
    And Click on search Box
    Then I should see suggestion for my search "glove"

  @smoke
  Scenario: As a Customer I search in the main field
    When I look for specific product "glove" in search box
    And Click on search Box
    Then I should see suggestion for my search "glove"
    And I should able to select first auto suggestion product
    Then I select first card from the list
    Then I select the select your size drop down 
    And I should be able to select size "M"
    Then As a customer able select option add to cart
    Then I should be able to perform go to cart
    And I should be able to see the order summary 799 and 1
    Then As a customer able to perform proceed to checkout
    Then I should be able to see login page with text "LOGIN"
    
  #@smoke
  #Scenario: As a Customer I can choose first card from search list
    #Given I look for an specific product in the menu
    #When I select first card from the list
    #Then I should see the filter options
    #And I search on the box filter for Title "3 jours de"
    #And Click on any Box
    #Then A link for review section should appear
