import groovy.xml.DOMBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;

import static io.restassured.RestAssured.given;

public class RestAssuredFakeRESTApi {

    File jsonDataInFile = new File("src/test/resources/Payloads/activity.json");

    @BeforeEach
    public void createRequestSpecification() {

//        requestSpec = new RequestSpecBuilder().
                RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/Activities" ;
    }

    @Test
    public void postRequest() {
//         httpRequest = RestAssured.given().auth().basic("postman", "password");
        Response response = given()
//                .auth()
//                .preemptive()
//                .basic("flowable", "flowable")
                .header("Content-type", "application/json")
                .and()
                .body(jsonDataInFile)
                .when()
                .post()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Hardlopen", response.jsonPath().getString("title"));
//        Assertions.assertEquals("123", response.jsonPath().getString("data.salary"));
//        Assertions.assertEquals("23", response.jsonPath().getString("data.age"));
//        Assertions.assertEquals("success", response.jsonPath().getString("status"));
    }
}
