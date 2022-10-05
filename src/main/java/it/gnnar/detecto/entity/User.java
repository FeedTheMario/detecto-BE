package it.gnnar.detecto.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@SelectBeforeUpdate
@DynamicUpdate(true)
@Getter
@Setter
public class User  {
    @Id
    @GeneratedValue
    private Long id;
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
