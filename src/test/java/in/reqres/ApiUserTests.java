package in.reqres;

import in.reqres.helper.UserData;
import in.reqres.models.pojo.PojoNameLastNameResponse;
import in.reqres.models.pojo.PojoUserRequest;
import in.reqres.models.pojo.PojoUserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpecs.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserTests {
    UserData user = new UserData();

    @Test
    @DisplayName("Check creating user")
    public void createUserAndCheckNameAndJob() {

        PojoUserRequest body = new PojoUserRequest();
        body.setName(user.getUserFirstName());
        body.setJob(user.getUserJob());
        PojoUserResponse response = given()
                .spec(userSpecRequest)
                .body(body)
                .when()
                .post()
                .then()
                .spec(userSpecResponseCreate)
                .extract()
                .as(PojoUserResponse.class);

        assertThat(response.getName()).isEqualTo(body.getName());
        assertThat(response.getJob()).isEqualTo(body.getJob());
    }

    @DisplayName("Check creating user with lastname and firstname")
    @Test
    public void checkUserLastNameAndFirstNameAndId() {

        PojoNameLastNameResponse response = given()
                .spec(userSpecRequest)
                .when()
                .get("/2")
                .then()
                .spec(userSpecResponseCheck)
                .extract()
                .as(PojoNameLastNameResponse.class);

        assertThat(response.getData().getFirst_name()).isEqualTo(user.getUserFirstName());
        assertThat(response.getData().getLast_name()).isEqualTo(user.getUserLastName());
        assertThat(response.getData().getId()).isEqualTo(2);
    }


}

