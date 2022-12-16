import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RestAssuredReqRes {

    File jsonDataInFile = new File("src/test/resources/Payloads/payload.json");


    @BeforeEach
    public void createRequestSpecification() {

//        requestSpec = new RequestSpecBuilder().
                RestAssured.baseURI="https://reqres.in/api/users" ;
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

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("test", response.jsonPath().getString("name"));
//        Assertions.assertEquals("123", response.jsonPath().getString("data.salary"));
//        Assertions.assertEquals("23", response.jsonPath().getString("data.age"));
//        Assertions.assertEquals("success", response.jsonPath().getString("status"));
    }
}
