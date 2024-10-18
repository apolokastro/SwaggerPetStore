package tests;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import payloads.Order;

public class StoreTest {
	
public Logger logger;
	
	Faker faker;
	Order storePayload;
	
	@BeforeClass(groups = {"RegressionStoreTest"})
	public void setUpData() {
		faker = new Faker();
		storePayload = new Order();
		
		storePayload.setId(faker.number().randomDigit());
		storePayload.setPetId(faker.number().randomDigit());
		storePayload.setQuantity(faker.number().randomDigit());
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  
		storePayload.setShipDate(dtf.format(now));
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1, groups = {"RegressionStoreTest"})
	public void GetPetStoreInventory() {
		
		logger.info("====================Starting test case====================");
		Response response = StoreEndpoints.getInventory();
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("PASSED");
		logger.info("=====================Ending test case=====================");
	}
	
	@Test(priority=2, groups = {"RegressionStoreTest"})
	public void PlacePetStoreOrder() {
		
		logger.info("====================Starting test case====================");
		Response response = StoreEndpoints.placeOrder(storePayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("PASSED");
		logger.info("=====================Ending test case=====================");
	}
	
	@Test(priority=3, groups = {"RegressionStoreTest"})
	public void GetPetStoreOrder() {
		
		logger.info("====================Starting test case====================");
		Response response = StoreEndpoints.getOrder(this.storePayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("PASSED");
		logger.info("=====================Ending test case=====================");
	}
	
	@Test(priority=4, groups = {"RegressionStoreTest"})
	public void DeletePetStoreOrder() {
		
		logger.info("====================Starting test case====================");
		Response response = StoreEndpoints.deleteOrder(this.storePayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("PASSED");
		logger.info("=====================Ending test case=====================");
	}
	
	
}
