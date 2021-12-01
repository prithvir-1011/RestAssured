package APITesting;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Demo_TestNg {
	
	public static String baseurl = "https://api.trello.com/";
	public String id;
	public String response;
	
	@Test(priority = 0)
	public void createboard()
	{
		RestAssured.baseURI = baseurl;
		
		Response response = given().queryParam("key","b9de334861afcbd1fcb3f35283e43c7c" )
		.queryParam("token","c3ca1bcaa91dad565ccc80f861a48b8b42ddcc451e77c7968ea4817242177092")
		.queryParam("name","prithvi-new").header("content-Type","application/json")
		
		.when()
		.post("/1/boards/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
	    String jsonresponse = response.asString();
	    JsonPath responsebody = new JsonPath(jsonresponse);
	    id = responsebody.get("id");
		
	}
	
	@Test(priority = 1)
	public void getboard()
	{
		RestAssured.baseURI = baseurl;
		
		Response response = given().queryParam("key","b9de334861afcbd1fcb3f35283e43c7c")
		.queryParam("token", "c3ca1bcaa91dad565ccc80f861a48b8b42ddcc451e77c7968ea4817242177092")
	
		.when()
		.get("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		System.out.println(jsonresponse);
		
		
	}
	
	@Test(priority = 2)
	public void deleteboard()
	{
         RestAssured.baseURI = baseurl;
		
		Response response = given().queryParam("key","b9de334861afcbd1fcb3f35283e43c7c" )
		.queryParam("token","c3ca1bcaa91dad565ccc80f861a48b8b42ddcc451e77c7968ea4817242177092")
		.queryParam("name","prithvi-new").header("content-Type","application/json")
		
		.when()
		.delete("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		
		.extract().response();
		String jsonresponse = response.asString();
		System.out.println(jsonresponse);
	}


	
}
