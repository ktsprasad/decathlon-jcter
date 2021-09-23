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
  Scenario: As a customer search products in the main field
    Given Close the location pop up
    When I look for specific product "glove" in search box
    And Click on search Box
    Then I should see suggestion for my search "glove"

  @smoke
  Scenario Outline: As a customer search multiple products search in the main field
    When I look for specific product "<Products>" in search box
    And Click on search Box
    Then I should see suggestion for my search "<Products>"
    And I should able to select first auto suggestion product
    Then I select first card from the list
    Then I select the select your size drop down
    And I should be able to select size "<Size>"
    Then As a customer able select option add to cart
    Then I should be able to perform go to cart
    And I should be able to see the order summary <Prize> and <Quantity>
    Then As a customer able to perform proceed to checkout
    Then I should be able to see login page with text "LOGIN"

    Examples: 
      | Products | Size | Prize | Quantity |
      | glove    | M    |   799 |        1 |
