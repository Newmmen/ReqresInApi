package in.reqres.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class UserSpecs {
    public static RequestSpecification userSpecRequest = with()
            .filter(new AllureRestAssured())
            .baseUri("https://reqres.in")
            .basePath("/api/users")
            .log().all()
            .contentType(ContentType.JSON);

    public static RequestSpecification userRegisterRequest = with()
            .filter(new AllureRestAssured())
            .baseUri("https://reqres.in")
            .basePath("/api/register")
            .log().all()
            .contentType(ContentType.JSON);

    public static RequestSpecification userSpecRequestLogin = with()
            .filter(new AllureRestAssured())
            .baseUri("https://reqres.in")
            .basePath("/api/login")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification userSpecResponseCreate = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();

    public static ResponseSpecification userSpecResponseWrongBody = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();

    public static ResponseSpecification userSpecResponseNoContent = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();

    public static ResponseSpecification userSpecResponseCheck = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
    public static ResponseSpecification userSpecResponseWrongLink = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();

}
