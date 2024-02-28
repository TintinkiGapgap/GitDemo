package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinitions extends Utils {

    RequestSpecification req;
    ResponseSpecification resSpec;
    Response response;

    // to reuse the same place ID for different scenario. Otherwise, the value of place IDwill be null
    static String place_id;


    TestDataBuild data = new TestDataBuild();
    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        //  ObjectMapper mapper = new ObjectMapper();
        //String s = mapper.writeValueAsString(p);
        //RestAssured.baseURI = "https://rahulshettyacademy.com";

        //ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        req = given().spec(requestSpecification()).log().all().body(data.addPlacePayload(name, language, address));

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        //constructor will be called with value of resource which you pass
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());

        if(method.equalsIgnoreCase("Post")) {
            response = req.when().post(resourceAPI.getResource());
        }
        else if (method.equalsIgnoreCase("Get"))
        {
            response = req.when().get(resourceAPI.getResource());
        }
        else if (method.equalsIgnoreCase("Put"))
        {
            response = req.when().put(resourceAPI.getResource());
        }
       // .then().spec(resSpec).log().all().extract().response();
    }
    @Then("the API call should be successful with Status Code {int}")
    public void the_api_call_should_be_successful_with_status_code(Integer statusCode) {
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response body should be {string}")
    public void in_response_body_should_be(String keyValue, String expectedValue) {

        Assert.assertEquals(getJsonPath(response, keyValue),expectedValue);
    }

    @Then("verify that the place ID created maps to {string} using {string}")
    public void verify_that_the_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        place_id = getJsonPath(response, "place_id");
        req = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "Get");
        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(actualName, expectedName);
    }

    @Given("updatePlace Payload")
    public void update_place_payload() throws IOException {
        req = given(). spec(requestSpecification()).body(data.updatePlacePayload(place_id));
    }

    @Given("deletePlace Payload")
    public void delete_place_payload() throws IOException {
        req = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }


}
