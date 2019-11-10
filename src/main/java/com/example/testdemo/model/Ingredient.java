package com.example.testdemo.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@EqualsAndHashCode(of = "id")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Ingredient {

    @Id
    @GeneratedValue(generator = "sec_ingredient", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sec_ingredient", sequenceName = "sec_ingredient", initialValue = 1, allocationSize=1)
    private Long id;

    private Integer amount;

    private String name;

}
