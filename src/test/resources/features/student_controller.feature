Feature: Testing StudentController API

  Scenario: Create a new student
    Given I have a new student with name "John" and presences number "2"
    When I make a POST request to "/students"
    Then the response status code should be 200
    And the response body should contain the student with name "John" and presences number "2"

  Scenario: Get all students
    Given I have existing students
    When I make a GET request to "/students"
    Then the response status code should be 200
    And the response body should contain all existing students
