package models;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String userID;
    private String expires;
    private int statusCode;
}
