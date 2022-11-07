package in.reqres;

import in.reqres.models.pojo.PojoNameLastNameResponse;
import in.reqres.models.pojo.PojoUserRequest;
import in.reqres.models.pojo.PojoUserResponse;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpecs.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiTests {

    @Test
    public void createUserAndCheckNameAndJob() {

        PojoUserRequest body = new PojoUserRequest();
        body.setName("John");
        body.setJob("QA");
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

        assertThat(response.getData().getFirst_name()).isEqualTo("Janet");
        assertThat(response.getData().getLast_name()).isEqualTo("Weaver");
        assertThat(response.getData().getId()).isEqualTo(2);
    }

    @Test
    public void check404WrongLink() {

        given()
                .spec(userSpecRequest)
                .when()
                .get("/855")
                .then()
                .spec(userSpecResponseWrongLink);
    }

}

