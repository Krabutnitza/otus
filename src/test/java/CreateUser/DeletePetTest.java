package CreateUser;

import api.PetApi;
import dto.PetDto;
import helper.CreatePetApi;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DeletePetTest {
    @Test
    @DisplayName("Удаление стандартного питомца по айди")
    public void deletePet() {
        PetApi petApi = new PetApi();
//        CreatePetApi pet = new CreatePetApi();
//        pet.createPet();

        petApi.deleltePet(12)
                .statusCode(200);

        ValidatableResponse response = petApi.deleltePet(12)
                .statusCode(HttpStatus.SC_OK);

        PetDto petResponse = response.extract().body().as(PetDto.class);
        System.out.println(petResponse);
    }
}
