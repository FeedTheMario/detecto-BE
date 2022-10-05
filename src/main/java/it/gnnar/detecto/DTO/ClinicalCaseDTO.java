package it.gnnar.detecto.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClinicalCaseDTO {

    private String name;
    private Integer age;
    private String sex;
    private Float weight;
    private Float height;
    private String description;
    private Integer difficulty;
    private String specialty;

    public ClinicalCaseDTO() {
    }
}
