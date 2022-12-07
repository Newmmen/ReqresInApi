package in.reqres;

import in.reqres.helper.UserData;
import in.reqres.models.pojo.UserRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpecs.basicRequestSpec;
import static in.reqres.specs.UserSpecs.basicResponseSpec;
import static io.restassured.RestAssured.given;

@Tag("Api")
public class ApiResponceCodeTests {
    UserData user = new UserData();

    @DisplayName("Checking 404 response code")
    @Test
    public void check404WrongLink() {

        given()
                .spec(basicRequestSpec)
                .when()
                .get("/api/users/855")
                .then()
                .spec(basicResponseSpec)
                .statusCode(404);
    }

    @DisplayName("Checking 400 response code")
    @Test
    public void check400WrongBody() {
        UserRequestDto body = new UserRequestDto();
        body.setEmail(user.getUserEmail());

        given()
                .spec(basicRequestSpec)
                .body(body)
                .when()
                .post("/api/login")
                .then()
                .spec(basicResponseSpec)
                .statusCode(400);
    }

    @DisplayName("Checking 204 response code")
    @Test
    public void check204WrongBody() {

        given()
                .spec(basicRequestSpec)
                .when()
                .delete("/api/users")
                .then()
                .spec(basicResponseSpec)
                .statusCode(204);
    }

}

