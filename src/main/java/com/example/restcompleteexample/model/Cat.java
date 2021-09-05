package com.example.restcompleteexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // создаст конструкто без параметров
@AllArgsConstructor // создаст конструктор со всеми параметрами
public class Cat {
    private String name;
    private int age;
}
