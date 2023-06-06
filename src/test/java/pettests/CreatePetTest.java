package pettests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import api.PetApi;
import dto.Category;
import dto.PetDto;
import dto.Tag;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreatePetTest {
    @Test
    @DisplayName("Создание питомца в магазине и проверка полей")
    public void createPetTest() {
        // Создаем питомца, проверяем все поля
        PetApi petApi = new PetApi();

        Category category = new Category();
        category.setId(12L);
        category.setName("Perchik");

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("https://www.india.com/wp-content/uploads/2015/10/538.jpg");

        Tag tag = new Tag();
        tag.setId(121L);
        tag.setName("Perchik");
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);

        PetDto pet = PetDto.builder()
                .category(category)
                .id(121L)
                .name("Perchik")
                .photoUrls(photoUrls)
                .status("available")
                .tags(tags)
                .build();

        petApi.createPet(pet)
                .statusCode(200);

        ValidatableResponse response = petApi.createPet(pet)
                .statusCode(HttpStatus.SC_OK);

        PetDto petResponse = response.extract().body().as(PetDto.class);

        Assertions.assertAll("Check create pet", () ->
                assertEquals(12, petResponse.getCategory().getId(), "Incorrect category id"), () ->
                assertEquals("Perchik", petResponse.getCategory().getName(), "Incorrect category name"), () ->
                assertEquals(121, petResponse.getId(), "Incorrect pet id"), () ->
                assertEquals("Perchik", petResponse.getName(), "Incorrect pet name"), () ->
                assertEquals("[https://www.india.com/wp-content/uploads/2015/10/538.jpg]", petResponse.getPhotoUrls().toString(), "Incorrect photo url"), () ->
                assertEquals("available", petResponse.getStatus(), "Incorrect status"), () ->
                assertEquals(121, petResponse.getTags().get(0).getId(), "Incorrect tag id"), () ->
                assertEquals("Perchik", petResponse.getTags().get(0).getName(), "Incorrect tag name"));
    }

    @Test
    @DisplayName("Создание неправильных полей и проверка на ошибку")
    public void checkIncorrectFields() {
        // Создаем питомца с "не теми" полями, что по идее должны быть, проверяем что они действительно не те

        PetApi petApi = new PetApi();

        Category category = new Category();
        category.setId(11L);
        category.setName("Boris");

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("https://www.india.com/wp-content/uploads/2015/10/538.jp");

        Tag tag = new Tag();
        tag.setId(123L);
        tag.setName("Barsik");
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);

        PetDto pet = PetDto.builder()
                .category(category)
                .id(122L)
                .name("Marsik")
                .photoUrls(photoUrls)
                .status("notAvailable")
                .tags(tags)
                .build();

        petApi.createPet(pet)
                .statusCode(200);
        ValidatableResponse response = petApi.createPet(pet)
                .statusCode(HttpStatus.SC_OK);

        PetDto petResponse = response.extract().body().as(PetDto.class);

        Assertions.assertAll("Check create pet", () ->
                assertNotEquals(12, petResponse.getCategory().getId(), "Incorrect category id"), () ->
                assertNotEquals("Perchik", petResponse.getCategory().getName(), "Incorrect category name"), () ->
                assertNotEquals(121, petResponse.getId(), "Incorrect pet id"), () ->
                assertNotEquals("Perchik", petResponse.getName(), "Incorrect pet name"), () ->
                assertNotEquals("[https://www.india.com/wp-content/uploads/2015/10/538.jpg]", petResponse.getPhotoUrls().toString(), "Incorrect photo url"), () ->
                assertNotEquals("available", petResponse.getStatus(), "Incorrect status"), () ->
                assertNotEquals(121, petResponse.getTags().get(0).getId(), "Incorrect tag id"), () ->
                assertNotEquals("Perchik", petResponse.getTags().get(0).getName(), "Incorrect tag name"));
    }
}