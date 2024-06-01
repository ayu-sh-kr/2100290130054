package dev.arhimedes.calculator.http.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter @Setter
@ToString
public class LoginRequest {
    private String clientID;
    private String clientSecret;
    private String ownerName;
    private String ownerEmail;
    private String rollNo;
    private String companyName;
}
