package CreateUser;

import api.PetApi;
import api.PetResponse;
import api.UserResponse;
import dto.PetDto;
import helper.CreatePetApi;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeletePetTest {
    @Test
    @DisplayName("Удаление стандартного питомца по айди")
    public void deletePet() {
        PetApi petApi = new PetApi();
        CreatePetApi pet = new CreatePetApi();
        pet.createPet();

        ValidatableResponse response = petApi.deleltePet(121)
                .statusCode(HttpStatus.SC_OK)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("121"));

        PetResponse petResponse = response.extract().body().as(PetResponse.class);

        Assertions.assertAll("Check delete pet", () ->
                assertEquals("unknown", petResponse.getType(), "Incorrect type"), () ->
                assertEquals("121", petResponse.getMessage(), "Incorrect message"), () ->
                assertEquals(200, petResponse.getCode(), "Incorrect code"));
    }

    @Test
    @DisplayName("")
    public void wrongStatusCode() {

    }
}
