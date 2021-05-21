package org.example.testtask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatDto {

    @Size(min = 2, max = 20)
    private String name;

    @Range(min = 0, max = 250)
    private int age;
}
