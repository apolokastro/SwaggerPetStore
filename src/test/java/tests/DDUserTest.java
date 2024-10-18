package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import endpoints.UserEndpoints;

import io.restassured.response.Response;
import payloads.User;
import utils.DataDriven;

public class DDUserTest {
	
	@Test(priority=1, groups = {"RegressionDDUserTest"}, dataProvider = "Data",  dataProviderClass = DataDriven.class)
	public void CreatePetStoreUser(String userID, String userName, String firstName, String lastName, String email, String password, String phone) {
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		Response response = UserEndpoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, groups = {"RegressionDDUserTest"}, dataProvider = "UserNames", dataProviderClass = DataDriven.class)
	public void DeletePetStoreUser(String userName) {
		
		Response response = UserEndpoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
