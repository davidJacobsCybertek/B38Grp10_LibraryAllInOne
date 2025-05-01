package com.library.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;

public class APIStepDefs {

    /********** US02 **********/
    //Global variables:
    RequestSpecification givenPart = RestAssured.given().log().uri();
    Response response;
    ValidatableResponse thenPart;
    JsonPath jp;

    String expectedID;

    Map<String, Object> randomData=new HashMap<>();

    @Given("Path param {string} is {string}")
    public void path_param_is(String pathParam, String value) {
        givenPart.pathParam(pathParam, value);
        expectedID = value; //declare globally
    }

    @Then("{string} field should be same with path param")
    public void field_should_be_same_with_path_param(String path) {
        String actualID = jp.getString(path);
        Assert.assertEquals(expectedID, actualID);
    }

    @Then("following fields should not be null")
    public void following_fields_should_not_be_null(List<String> allPaths) {

        for (String eachPath : allPaths) {
            thenPart.body(eachPath, notNullValue());
        }

    }
}
