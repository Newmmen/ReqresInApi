package in.reqres;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;

public class ApiTests {

    @Test
    public void createUserAndCheckNameAndJob() {
        String name = "John";
        String job = "QA";
        String body = format("{ \"name\": \"%s\", " +
                "\"job\": \"%s\" }", name, job);

        given()
                .contentType(JSON)
                .body(body)
                .log().all()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is(name))
                .body("job", is(job));
    }

    @Test
    public void checkUserLastNameAndFirstNameAndId() {

        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
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

