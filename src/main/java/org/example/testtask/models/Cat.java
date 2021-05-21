package org.example.testtask.models;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Cat {

    private int id;

    @NonNull
    private String name;

    @NonNull
    private int age;
}
