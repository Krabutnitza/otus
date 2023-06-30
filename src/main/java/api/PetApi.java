package api;

import static io.restassured.RestAssured.given;

import dto.PetDto;
import helper.TestPreparation;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class PetApi {
    private TestPreparation testPrep = new TestPreparation();
    private static final String PET = "/pet";
    private RequestSpecification respec;

    public PetApi() {
        respec = given()
                .baseUri(testPrep.getBaseUrl())
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse createPet(PetDto petDto) {
        return given(respec)
                .log().all()
                .body(petDto)
                .when()
                .post(PET)
                .then()
                .log().all();
    }

    public ValidatableResponse deleltePet(Long petId) {
        return given(respec)
                .log().all()
                .basePath("/pet/{petId}")
                .pathParams("petId", petId)
                .when()
                .delete()
                .then()
                .log().all();
    }
}
