package api;

import dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String petShopUrl = "https://petstore.swagger.io/v2";
    private static final String USER = "/user";
    private RequestSpecification respec;

    public UserApi() {
        respec = given()
                .baseUri(petShopUrl)
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
