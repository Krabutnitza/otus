package api;

import static io.restassured.RestAssured.given;

import dto.UserDto;
import helper.TestPreparation;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class UserApi {
    private TestPreparation testPrep = new TestPreparation();
    private static final String USER = "/user";
    private RequestSpecification respec;

    public UserApi() {
        respec = given()
                .baseUri(testPrep.getBaseUrl())
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse createUser(UserDto userDto) {
        return given(respec)
                .log().all()
                .body(userDto)
                .when()
                .post(USER)
                .then()
                .log().all();
    }
}
