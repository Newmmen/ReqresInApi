package in.reqres;

import in.reqres.helper.UserData;
import in.reqres.models.pojo.ErrorResponseDto;
import in.reqres.models.pojo.UserRequestDto;
import in.reqres.models.pojo.UserResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpecs.basicRequestSpec;
import static in.reqres.specs.UserSpecs.basicResponseSpec;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("Api")
public class ApiAuthorisationTests {
    UserData user = new UserData();


    @DisplayName("Check register with undefined user")
    @Test
    public void undefinedRegisterUser() {
        UserRequestDto body = new UserRequestDto();
        body.setEmail(user.getUserUndefinedEmail());
        body.setPassword(user.getUserUndefinedPassword());
        ErrorResponseDto response = given()
                .spec(basicRequestSpec)
                .body(body)
                .when()
                .post("/api/register")
                .then()
                .spec(basicResponseSpec)
                .statusCode(400)
                .extract()
                .as(ErrorResponseDto.class);

        assertThat(response.getError()).isEqualTo("Note: Only defined users succeed registration");

    }


    @DisplayName("Check register user")
    @Test
    public void registerUser() {
        UserRequestDto body = new UserRequestDto();
        body.setEmail(user.getUserEmail());
        body.setPassword(user.getUserPassword());
        UserResponseDto response = given()
                .spec(basicRequestSpec)
                .body(body)
                .when()
                .post("/api/register")
                .then()
                .spec(basicResponseSpec)
                .statusCode(200)
                .extract()
                .as(UserResponseDto.class);

        assertThat(response.getToken()).isNotNull();
        assertThat(response.getId()).isNotNull();

    }

    @Test
    @DisplayName("Check login user")
    public void loginUser() {
        UserRequestDto body = new UserRequestDto();
        body.setEmail(user.getUserEmail());
        body.setPassword(user.getUserPassword());

        UserResponseDto response = given()
                .spec(basicRequestSpec)
                .when()
                .body(body)
                .post("/api/login")
                .then()
                .spec(basicResponseSpec)
                .statusCode(200)
                .extract()
                .as(UserResponseDto.class);

        assertThat(response.getToken()).isNotNull();


    }

    @DisplayName("Check login with undefined user")
    @Test
    public void undefinedloginUser() {
        UserRequestDto body = new UserRequestDto();
        body.setEmail(user.getUserUndefinedEmail());
        body.setPassword(user.getUserUndefinedPassword());

        ErrorResponseDto response = given()
                .spec(basicRequestSpec)
                .when()
                .body(body)
                .post("/api/login")
                .then()
                .spec(basicResponseSpec)
                .statusCode(400)
                .extract()
                .as(ErrorResponseDto.class);

        assertThat(response.getError()).isEqualTo("user not found");


    }

}
