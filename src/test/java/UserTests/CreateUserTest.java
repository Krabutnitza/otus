package UserTests;

import api.UserResponse;
import dto.UserDto;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import api.UserApi;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserTest {
    @Test
    @DisplayName("Создание пользователя и проверка всех полей (тест с занятия)")
    public void createUser() {
        // Создаем юзера, проверяем поля

        UserApi userApi = new UserApi();
        UserDto user = UserDto.builder()
                .email("liza@mail.ru")
                .id((long) 500)
                .firstName("Liza")
                .lastName("Liza")
                .password("123456")
                .userName("krabutnitza")
                .userStatus((long) 500)
                .build();

        ValidatableResponse response = userApi.createUser(user)
                .statusCode(HttpStatus.SC_OK)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("500"));

        UserResponse userResponse = response.extract().body().as(UserResponse.class);

        Assertions.assertAll("Check create user", () ->
                assertEquals("unknown", userResponse.getType(), "Incorrect type"), () ->
                assertEquals("500", userResponse.getMessage(), "Incorrect message"), () ->
                assertEquals(200, userResponse.getCode(), "Incorrect code"));
    }
}
