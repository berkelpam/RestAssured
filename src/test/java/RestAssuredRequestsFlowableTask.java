import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RestAssuredRequestsFlowableTask {

    File jsonDataInFile = new File("src/test/resources/Payloads/flowabletask.json");


    @BeforeEach
    public void createRequestSpecification() {

//        requestSpec = new RequestSpecBuilder().
                RestAssured.baseURI="http://localhost:9095/flowable-rest/runtime/tasks" ;
    }

    @Test
    public void postRequest() {
//         httpRequest = RestAssured.given().auth().basic("postman", "password");
        Response response = given()
                .auth()
                .preemptive()
                .basic("rest-admin", "test")
                .header("Content-type", "application/json")
                .and()
                .body(jsonDataInFile)
                .when()
                .post()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("test", response.jsonPath().getString("data.name"));
        Assertions.assertEquals("123", response.jsonPath().getString("data.salary"));
        Assertions.assertEquals("23", response.jsonPath().getString("data.age"));
        Assertions.assertEquals("success", response.jsonPath().getString("status"));
    }
}
