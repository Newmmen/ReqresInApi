package in.reqres;

import in.reqres.helper.UserData;
import in.reqres.models.pojo.PojoUserRequest;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpecs.*;
import static io.restassured.RestAssured.given;

public class ApiResponceCodeTests {
    UserData user = new UserData();

    @Test
    public void check404WrongLink() {

        given()
                .spec(userRegisterRequest)
                .when()
                .get("/855")
                .then()
                .spec(userSpecResponseWrongLink);
    }

    @Test
    public void check400WrongBody() {
        PojoUserRequest body = new PojoUserRequest();
        body.setEmail(user.getUserEmail());

        given()
                .spec(userSpecRequestLogin)
                .body(body)
                .when()
                .post()
                .then()
                .spec(userSpecResponseWrongBody);
    }

    @Test
    public void check204WrongBody() {

        given()
                .spec(userSpecRequest)
                .when()
                .delete()
                .then()
                .spec(userSpecResponseNoContent);
    }

}

