package APITesting;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class e_commerce {
	
	public String accessToken;
	public String id;
	
	public static String baseurl = "https://ecommerceservice.herokuapp.com";
	
	@Test(priority = 0,enabled = false)
	public void sign_up()
	{
		RestAssured.baseURI = baseurl;
		
		String requestbody = "{\r\n"
				+ "	\"email\": \"prith112800@gmail.com\",\r\n"
				+ "	\"password\": \"priths@123\"\r\n"
				+ "}\r\n"
				+ "";

		
		Response response = given()
				.header("content-Type","application/json")
				.body(requestbody)
				
				.when()
				.post("/user/signup")
				
				.then()
				.assertThat().statusCode(201).contentType(ContentType.JSON)
				
				.extract().response();
		
				
		String jsonresponse = response.asString();
		
		JsonPath responsebody =new JsonPath(jsonresponse);
		
		System.out.println(responsebody.get("message"));
	}
	
	@Test(priority = 0)
	
	public void login()
	{
         RestAssured.baseURI = baseurl;
		
		String requestbody = "{\r\n"
				+ "	\"email\": \"prith112800@gmail.com\",\r\n"
				+ "	\"password\": \"priths@123\"\r\n"
				+ "}\r\n"
				+ "";

		
		Response response = given()
				.header("content-Type","application/json")
				.body(requestbody)
				
				.when()
				.post("/user/login")
				
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				
				.extract().response();
		
				
		String jsonresponse = response.asString();
		
		JsonPath responsebody =new JsonPath(jsonresponse);
		
		System.out.println(responsebody.get("accessToken"));
		accessToken = responsebody.get("accessToken");
		
	}
	
	@Test(priority = 1)
	 public void getusers()
	 {
        RestAssured.baseURI = baseurl;
		

		
		Response response = given()
				.header("content-Type","application/json")
				.header("Authorization", "Bearer "+accessToken)
				//.body(requestbody)
				
				.when()
				.get("/user")
				
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				
				.extract().response();
		
				
		String jsonresponse = response.asString();
		
		JsonPath responsebody =new JsonPath(jsonresponse);
		
		System.out.println(responsebody.get("users[200]._id"));
		id = responsebody.get("users[200]._id");
	 }
	
	@Test(priority = 2)
	public void delete()
	{
		 RestAssured.baseURI = baseurl;

		
		Response response = given()
				.header("content-Type","application/json")
				.header("Authorization", "Bearer "+accessToken)
				
				
				.when()
				.delete("/user/"+id)
				
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON)
				
				.extract().response();
		
				
		String jsonresponse = response.asString();
		
		JsonPath responsebody =new JsonPath(jsonresponse);
		
		System.out.println(responsebody.get("message"));
		//id = responsebody.get("users[200]._id");
	}

}
