package endpoints;


import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.User;


public class UserEndpoints {
	
	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createUser(User payload) {
		String url = getURL().getString("post_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(url);
	
		return response; 
	}
	
	public static Response getUser(String username) {
		String url = getURL().getString("get_url");
		Response response = given()
			.pathParam("username", username)
		.when()
			.get(url);
	
		return response; 
	}
	
	public static Response updateUser(String username, User payload) {
		String url = getURL().getString("update_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(url);
	
		return response; 
	}
	
	public static Response deleteUser(String username) {
		String url = getURL().getString("delete_url");
		Response response = given()
			.pathParam("username", username)
		.when()
			.delete(url);
	
		return response; 
	}
	
	

}
