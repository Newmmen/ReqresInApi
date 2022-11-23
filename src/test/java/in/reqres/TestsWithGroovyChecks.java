package in.reqres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpecs.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class TestsWithGroovyChecks {

    @Test
    @DisplayName("Check email with Groovy")
    public void checkEmailUsingGroovy() {
        given()
                .spec(userSpecRequest)
                .when()
                .get()
                .then()
                .spec(userSpecResponseCheck)
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("eve.holt@reqres.in"));
    }

    @Test
    @DisplayName("Check color with Groovy")
    public void checkColorUsingGroovy() {

        given()
                .spec(userSpecRequestUnknown)
                .when()
                .get()
                .then()
                .spec(userSpecResponseCheck)
                .body("data.findAll{it.color}.color.flatten()", hasItem("#E2583E"));

    }
}



