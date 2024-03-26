package org.ols.qaprep.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.ols.qaprep.controller.StudentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerSteps {

    @Autowired
    private StudentController studentController;

    private MockMvc mockMvc;
    private ResultActions resultActions;

    @Given("I have a new student with name {string}")
    public void i_have_a_new_student_with_name_and_present_status(String name) throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        String requestBody = "{\"name\": \"" + name + "\"}";
        resultActions = mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
    }

    @When("I make a POST request to {string}")
    public void i_make_a_post_request_to(String endpoint) {
        // No action needed here, already performed in the Given step
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) throws Exception {
        resultActions.andExpect(status().is(expectedStatusCode));
    }

    @And("the response body should contain the student with name {string} and presences number {string}")
    public void the_response_body_should_contain_the_student_with_name_and_present_status(String name, String presenceNo) throws Exception {
        resultActions.andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.noOfPresence").value(Integer.valueOf(presenceNo)));
    }

    // Similar step definitions for other scenarios...
}
