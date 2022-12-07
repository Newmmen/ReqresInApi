package in.reqres;

import in.reqres.helper.UserData;
import in.reqres.models.pojo.UserDataResponseDto;
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
public class ApiUserTests {
    UserData user = new UserData();

    @Test
    @DisplayName("Check creating user")
    public void createUserAndCheckNameAndJob() {

        UserRequestDto body = new UserRequestDto();
        body.setName(user.getUserFirstName());
        body.setJob(user.getUserJob());
        UserResponseDto response = given()
                .spec(basicRequestSpec)
                .body(body)
                .when()
                .post("/api/users")
                .then()
                .spec(basicResponseSpec)
                .statusCode(201)
                .extract()
                .as(UserResponseDto.class);

        assertThat(response.getName()).isEqualTo(body.getName());
        assertThat(response.getJob()).isEqualTo(body.getJob());
    }

    @DisplayName("Check creating user with lastname and firstname")
    @Test
    public void checkUserLastNameAndFirstNameAndId() {

        UserDataResponseDto response = given()
                .spec(basicRequestSpec)
                .when()
                .get("/api/users/2")
                .then()
                .spec(basicResponseSpec)
                .statusCode(200)
                .extract()
                .as(UserDataResponseDto.class);

        assertThat(response.getData().getFirstName()).isEqualTo(user.getUserFirstName());
        assertThat(response.getData().getLastName()).isEqualTo(user.getUserLastName());
        assertThat(response.getData().getId()).isNotNull();
    }


}

