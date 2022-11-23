package in.reqres;

import in.reqres.helper.UserData;
import in.reqres.models.pojo.PojoErrorResponse;
import in.reqres.models.pojo.PojoUserRequest;
import in.reqres.models.pojo.PojoUserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpecs.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiAuthorisationTests {
    UserData user = new UserData();


    @DisplayName("Check register with undefined user")
    @Test
    public void UndefinedRegisterUser() {
        PojoUserRequest body = new PojoUserRequest();
        body.setEmail(user.getUserUndefinedEmail());
        body.setPassword(user.getUserUndefinedPassword());
        PojoErrorResponse response = given()
                .spec(userRegisterRequest)
                .body(body)
                .when()
                .post()
                .then()
                .spec(userSpecResponseWrongBody)
                .extract()
                .as(PojoErrorResponse.class);

        assertThat(response.getError()).isEqualTo("Note: Only defined users succeed registration");

    }



    @DisplayName("Check register user")
    @Test
    public void RegisterUser() {
        PojoUserRequest body = new PojoUserRequest();
        body.setEmail(user.getUserEmail());
        body.setPassword(user.getUserPassword());
        PojoUserResponse response = given()
                .spec(userRegisterRequest)
                .body(body)
                .when()
                .post()
                .then()
                .spec(userSpecResponseCheck)
                .extract()
                .as(PojoUserResponse.class);

        assertThat(response.getToken()).isEqualTo(user.getToken());
        assertThat(response.getId()).isEqualTo("4");

    }

    @Test
    @DisplayName("Check login user")
    public void loginUser() {
        PojoUserRequest body = new PojoUserRequest();
        body.setEmail(user.getUserEmail());
        body.setPassword(user.getUserPassword());

        PojoUserResponse response = given()
                .spec(userSpecRequestLogin)
                .when()
                .body(body)
                .post()
                .then()
                .spec(userSpecResponseCheck)
                .extract()
                .as(PojoUserResponse.class);

        assertThat(response.getToken()).isEqualTo(user.getToken());


    }

    @DisplayName("Check login with undefined user")
    @Test
    public void UndefinedloginUser() {
        PojoUserRequest body = new PojoUserRequest();
        body.setEmail(user.getUserUndefinedEmail());
        body.setPassword(user.getUserUndefinedPassword());

        PojoErrorResponse response = given()
                .spec(userSpecRequestLogin)
                .when()
                .body(body)
                .post()
                .then()
                .spec(userSpecResponseWrongBody)
                .extract()
                .as(PojoErrorResponse.class);

        assertThat(response.getError()).isEqualTo("user not found");


    }

}
