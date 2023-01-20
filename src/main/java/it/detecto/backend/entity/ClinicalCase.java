package it.detecto.backend.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
public class ClinicalCase {

  @Id
  private String id;
  private String name;
  private Integer age;
  private String sex; //TODO: enum BiologicalSex
  private Float weight;
  private Float height;
  private String description;
  private Integer difficulty;
  private String specialty;
  @DBRef
  private Avatar avatar;
  private String status;
  @DBRef
  private User author;

  public ClinicalCase() {
  }

  public ClinicalCase(String name, Integer age, String sex, Float weight, Float height, String description, Integer difficulty,
    String specialty, Avatar avatar, String status, User author) {
    this.name = name;
    this.age = age;
    this.sex = sex;
    this.weight = weight;
    this.height = height;
    this.description = description;
    this.difficulty = difficulty;
    this.specialty = specialty;
    this.avatar = avatar;
    this.status = status;
    this.author = author;
  }
}
