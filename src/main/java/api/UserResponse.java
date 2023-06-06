package api;

import lombok.Data;

@Data
public class UserResponse {
    private Long code;
    private String message;
    private String type;
}
