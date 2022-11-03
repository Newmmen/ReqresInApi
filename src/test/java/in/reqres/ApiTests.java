package in.reqres;

import in.reqres.models.pojo.PojoNameLastNameResponse;
import in.reqres.models.pojo.PojoUserRequest;
import in.reqres.models.pojo.PojoUserResponse;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

public class ApiTests {

    @Test
    public void createUserAndCheckNameAndJob() {


        PojoUserRequest body = new PojoUserRequest();
        body.setName("John");
        body.setJob("QA");
        PojoUserResponse response = given()
                .contentType(JSON)
                .body(body)
                .log().all()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .extract()
                .as(PojoUserResponse.class);

        assertThat(response.getName()).isEqualTo(body.getName());
        assertThat(response.getJob()).isEqualTo(body.getJob());
    }

    @Test
    public void checkUserLastNameAndFirstNameAndId() {

        PojoNameLastNameResponse response = given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .as(PojoNameLastNameResponse.class);

        assertThat(response.getData().getFirst_name()).isEqualTo("Janet");
        assertThat(response.getData().getLast_name()).isEqualTo("Weaver");
        assertThat(response.getData().getId()).isEqualTo(2);


    }

    @Test
    public void checkAllKnownUsersId() {
        List<Integer> expectedUsersId = new ArrayList<>(Arrays.asList(7, 8, 9, 10, 11, 12));

        String json = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .extract().asString();

        JsonPath jsonPath = new JsonPath(json).setRootPath("data");
        List<Integer> actualUsersId = jsonPath.get("id");
        Assertions.assertArrayEquals(actualUsersId.toArray(), expectedUsersId.toArray());
    }


    @Test
    public void check404WrongLink() {

        String linkWithMistake = "https://reqres.in/ap/user/2";

        given()
                .when()
                .get(linkWithMistake)
                .then()
                .statusCode(404);

    }

    @Test
    public void check400WrongBody() {
        String wrongBody = ("{ \"name\": \"Sam\", " +
                "\"job\": \"QA\" ");

        given()
                .contentType(JSON)
                .body(wrongBody)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(400);

    }

}

