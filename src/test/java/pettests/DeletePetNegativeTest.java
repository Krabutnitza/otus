package pettests;

import api.PetApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeletePetNegativeTest {
    @Test
    @DisplayName("Получение негативного кода ответа на запрос")
    public void wrongStatusCode() {
        // Пытаемся удалить не существующий айдишник (питомца), получаем ошибку

        PetApi petApi = new PetApi();

        petApi.deleltePet(999L)
                .statusCode(404);
    }
}
