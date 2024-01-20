package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

public class Stepdef extends Utils{

	RequestSpecification res;		
	ResponseSpecification resspec;
	Response response;
	
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place payload with {string}  {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
				
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name , language , address));
		
		System.out.println("the request value is" + res);
	}	
		
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource , String method) {
	    // Write code here that turns the phrase above into concrete actions
		
			//Invoke constructor ApiResources with value of this resource 
			ApiResources resourceApi=ApiResources.valueOf(resource);
			System.out.println(resourceApi.getResource());
			resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build(); 
			
			if(method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceApi.getResource());
			else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceApi.getResource());
						
			
			String responsestring = response.asString();
			System.out.println("the response value is" + responsestring);	
	}
	
		
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions

			assertEquals(response.getStatusCode(),200);
	}
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(getJsonPath(response,keyvalue),expectedvalue);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		String place_id = getJsonPath(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_post_http_request(resource,"GET");
		
		String actualname = getJsonPath(response,"name");
		assertEquals(actualname,expectedname);
	}

	
	
}
