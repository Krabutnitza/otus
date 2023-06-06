package helper;

import api.PetApi;
import dto.Category;
import dto.PetDto;
import dto.Tag;

import java.util.ArrayList;
import java.util.List;

public class CreatePetApi {
    // Создание стандартного питомца
    public void createPet() {
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
    }
}