package api;

import lombok.Data;

@Data
public class PetResponse {
    private Long code;
    private String message;
    private String type;
}
