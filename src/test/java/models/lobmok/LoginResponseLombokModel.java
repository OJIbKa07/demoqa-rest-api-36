package models.lobmok;

import lombok.Data;

@Data
public class LoginResponseLombokModel {
    private String token;
    private String userID;
    private String expires;
    private int statusCode;
}
