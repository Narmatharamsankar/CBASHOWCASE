package stepdefenitions;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.github.javafaker.Faker;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.*;

public class PetStoreApiStepDefinition {

	public static RequestSpecification inputRequest;
	public static Response response;
	public static Pets pet = null;

	@Given("I have a pet named {string} with status {string}")
	public void i_have_a_pet_named_with_status(String name, String status) {
		// create pet object
		pet = new Pets.Builder().withName(name).withStatus(Status.valueOf(status)).build();

	}

	@When("I try to add the pet to the petstore")
	public void i_try_to_add_the_pet_to_the_petstore() {

		response = given().body(pet, ObjectMapperType.GSON).accept(ContentType.JSON).contentType(ContentType.JSON)
				.post();
	
		Long id = response.getBody().as(Pets.class, ObjectMapperType.GSON).getId();
		pet.setId(id);

	}

	@Then("The response status code is returned as {int}")
	public void the_response_status_code_is_returned_as(Integer statuscode) {
		response.then().assertThat().statusCode(statuscode);
	}

	@And("The pet is successfully added to the petstore")
	public void the_pet_is_successfully_added_to_the_petstore() {

		String actualName = response.getBody().as(Pets.class, ObjectMapperType.GSON).getName();
		Assert.assertEquals(actualName, pet.getName());
	}

	@Given("I create  pet with invalid jsondata")
	public void i_create_pet_with_invalid_jsondata() {
		response = given().body(new File("src/test/resources/newPet_invalid_data.json")).accept(ContentType.JSON)
				.contentType(ContentType.JSON).post();
		
	}

	@When("I try to update the image for the pet")
	public void i_try_to_update_the_image_for_the_pet() {

		File f = new File("src/test/resources/doggie.jpg");
		pet.setPhotoUrls("src/test/resources/doggie.jpg");
		response = given().contentType("multipart/form-data").multiPart("file", f).pathParam("petId", pet.getId())
				.when().post("/{petId}" + "/uploadImage");
		
	}

	@When("I try to update the status of the pet to {string}")
	public void i_try_to_update_the_status_of_the_pet_to(String status) {

		pet.setStatus(Status.valueOf(status));
		response = given().body(pet, ObjectMapperType.GSON).accept(ContentType.JSON).contentType(ContentType.JSON)
				.post();

		
	}

	@Then("The status is updated for the pet")
	public void the_status_is_updated_for_the_pet() {
		
		Assert.assertEquals(response.getBody().asString().contains(pet.getStatus().name()), true);

	}

	@Given("I search for pets with status {string}")
	public void i_search_for_pets_with_status(String status) {
		response = given().queryParam("status", status).when().get("/findByStatus");
	}

	@Then("the response has all the pets with status {string}")
	public void the_response_has_all_the_pets_with_status(String status) {

		String[] statuses = response.jsonPath().getList("status").toArray(new String[0]);
		for (String s : statuses) {
			Assert.assertEquals(s, status);
		}

	}

	@When("The pet is searched with id")
	public void the_pet_is_searched_with_id() {
		 response =  given() 
				    .get("/"+pet.getId());  
				  			  
   	    
	}
	
	@When("I try to delete the pet")
	public void i_try_to_delete_the_pet() {
		response = given()
    			  .delete("/"+pet.getId());
	   
	}
	
	@Given("I try to find a pet with invalid id {string}")
	public void i_try_to_find_a_pet_with_invalid_id(String id) {
		 response =  given() 
				    .get("/"+id);  
				  			  
	  		
	}
	
	@When("I try to update the pet status code in form data to {string}")
	public void i_try_to_update_the_pet_status_code_in_form_data_to(String status) {
	 
		  response=given()
				   .formParam("status",status)
			   .post("/"+pet.getId());
		 		  
	}

	@Given("I try to delete a pet with invalid id {string}")
	public void i_try_to_delete_a_pet_with_invalid_id(String id) {
		response = given()
  			  .delete("/"+id);
	  
	}

	
	

}
