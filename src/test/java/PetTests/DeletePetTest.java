package PetTests;

import api.PetApi;
import api.PetResponse;
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
        // Создаем питомца, потом его удаляем и проверяем поля

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
    @DisplayName("Получение негативного кода ответа на запрос")
    public void wrongStatusCode() {
        // Пытаемся удалить не существующий айдишник (питомца), получаем ошибку

        PetApi petApi = new PetApi();

        petApi.deleltePet(999)
                .statusCode(404);
    }
}
