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
                RestAssured.baseURI="http://localhost:8090/process-api/runtime/tasks" ;
//            RestAssured.baseURI="http://localhost:8070/public-lighting-customer-requests/v1/requests";
    }

    @Test
    public void updateExistingRequest() {
//         httpRequest = RestAssured.given().auth().basic("postman", "password");
        Response response = given()
                .auth()
                .preemptive()
                .basic("flowable", "flowable")
                .header("Content-type", "application/json")
                .and()
                .body(jsonDataInFile)
                .when()
                .post()
                .then()
                .extract().response();

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("Beoordeel wijziging", response.jsonPath().getString("name"));

    }
}
