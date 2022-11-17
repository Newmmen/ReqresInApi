package in.reqres;

import in.reqres.models.pojo.PojoErrorResponse;
import in.reqres.models.pojo.PojoUserRequest;
import in.reqres.models.pojo.PojoUserResponse;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpecs.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiAuthorisationTests {

    @Test
    public void UndefinedRegisterUser(){
        PojoUserRequest body = new PojoUserRequest();
        body.setEmail("sample@mail.ru");
        body.setPassword("1234");
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

    @Test
    public void RegisterUser(){
        PojoUserRequest body = new PojoUserRequest();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");
        PojoUserResponse response = given()
                .spec(userRegisterRequest)
                .body(body)
                .when()
                .post()
                .then()
                .spec(userSpecResponseCheck)
                .extract()
                .as(PojoUserResponse.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
        assertThat(response.getId()).isEqualTo("4");

    }

    @Test
    public void loginUser(){
        PojoUserRequest body = new PojoUserRequest();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");

        PojoUserResponse response = given()
                .spec(userSpecRequestLogin)
                .when()
                .body(body)
                .post()
                .then()
                .spec(userSpecResponseCheck)
                .extract()
                .as(PojoUserResponse.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");


    }
    @Test
    public void UndefinedloginUser(){
        PojoUserRequest body = new PojoUserRequest();
        body.setEmail("eve.lt@reqres.in");
        body.setPassword("pistl");

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
