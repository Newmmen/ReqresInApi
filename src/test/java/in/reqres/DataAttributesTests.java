package in.reqres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpecs.basicRequestSpec;
import static in.reqres.specs.UserSpecs.basicResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

@Tag("Api")
public class DataAttributesTests {

    @Test
    @DisplayName("Check user email")
    public void checkUserEmail() {
        given()
                .spec(basicRequestSpec)
                .when()
                .get("/api/users")
                .then()
                .spec(basicResponseSpec)
                .statusCode(200)
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("eve.holt@reqres.in"));
    }

    @Test
    @DisplayName("Check color")
    public void checkColor() {

        given()
                .spec(basicRequestSpec)
                .when()
                .get("/api/unknown")
                .then()
                .spec(basicResponseSpec)
                .statusCode(200)
                .body("data.findAll{it.color}.color.flatten()", hasItem("#E2583E"));

    }
}



