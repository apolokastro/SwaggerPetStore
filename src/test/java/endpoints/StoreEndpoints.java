package endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Order;

public class StoreEndpoints {
	
	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response getInventory() {
		String url = getURL().getString("get_inventory");
		Response response = given()	
		.when()
			.get(url);
	
		return response; 
	}
	
	public static Response placeOrder(Order payload) {
		String url = getURL().getString("post_order");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(url);
	
		return response;  
	}
	
	public static Response getOrder(int orderId) {
		String url = getURL().getString("get_order");
		Response response = given()
				.pathParam("orderId", orderId)
		.when()
			.get(url);
	
		return response;  
	}
	
	public static Response deleteOrder(int orderId) {
		String url = getURL().getString("delete_order");
		Response response = given()
				.pathParam("orderId", orderId)
		.when()
			.delete(url);
	
		return response;  
	}
}
