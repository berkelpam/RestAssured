import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredRequestsRequestWithAdditionalLength {

    File jsonDataInFile = new File("src/test/resources/Payloads/requestwithadditionallength.json");

    @BeforeEach
    public void createRequestSpecification() {

          RestAssured.baseURI="http://localhost:8070/public-lighting-customer-requests/v1/requests";
    }

    @Test
    public void submitNewRequestWithAdditionalLength() {
//         httpRequest = RestAssured.given().auth().basic("postman", "password");
        Response response = given()
                .auth()
                .preemptive()
                .basic("username", "password")
                .header("Content-type", "application/json")
                .and()
                .body(jsonDataInFile)
                .when()
                .post()
                .then()
                .extract().response();

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertTrue(response.headers().getValue("X-Object-ID").matches("^OVA[0-9]{10}$"));
    }


    @Test
    public void verifyExistingRequestCanBeUpdated() {
//         httpRequest = RestAssured.given().auth().basic("postman", "password");

        Response responseOne = given()
                .auth()
                .preemptive()
                .basic("username", "password")
                .header("Content-type", "application/json")
                .and()
                .body(jsonDataInFile)
                .when()
                .post()
                .then()
                .extract().response();

        Assertions.assertEquals(201, responseOne.statusCode());
        Assertions.assertTrue(responseOne.headers().getValue("X-Object-ID").matches("^OVA[0-9]{10}$"));
        String aanvraagnummer = responseOne.headers().getValue("X-Object-ID");

        File jsonDataInFile = new File("src/test/resources/Payloads/updaterequestwithadditionallength.json");
        String newURL= RestAssured.baseURI+"/"+ aanvraagnummer;
        Response response = given()
                .baseUri(newURL)
                .auth()
                .preemptive()
                .basic("username", "password")
                .header("Content-type", "application/json")
                .and()
                .body(jsonDataInFile)
                .when()
                .patch()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }
}