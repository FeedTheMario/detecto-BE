package it.detecto.backend.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class User  {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String[] activeTokens;
    private String passwordHash;
    private String rotateToken;


    public User() {
    }
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
