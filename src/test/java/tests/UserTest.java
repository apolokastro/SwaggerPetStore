package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.UserEndpoints;
import io.restassured.response.Response;
import payloads.User;

public class UserTest {
	
	public Logger logger;
	
	Faker faker;
	User userPayload;
	
	
	@BeforeClass(groups = {"RegressionUserTest"})
	public void setUpData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1, groups = {"RegressionUserTest"})
	public void CreatePetStoreUser() {
		
		logger.info("====================Starting test case====================");
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("PASSED");
		logger.info("=====================Ending test case=====================");
	}
	
	@Test(priority=2, groups = {"RegressionUserTest"})
	public void GetPetStoreUserDetails() {
		
		logger.info("====================Starting test case====================");
		Response response = UserEndpoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("PASSED");
		logger.info("=====================Ending test case=====================");
	}
	
	@Test(priority=3, groups = {"RegressionUserTest"})
	public void UpdatePetStoreUserDetails() {
		
		logger.info("====================Starting test case====================");
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		
		Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseAfterUpdate = UserEndpoints.getUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info("PASSED");
		logger.info("=====================Ending test case=====================");
	}
	
	@Test(priority=4, groups = {"RegressionUserTest"})
	public void DeletePetStoreUser() {
		
		logger.info("====================Starting test case====================");
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("PASSED");
		logger.info("=====================Ending test case=====================");
	}
}
