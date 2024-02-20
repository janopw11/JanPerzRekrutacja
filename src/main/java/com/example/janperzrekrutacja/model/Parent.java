package com.example.janperzrekrutacja.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Class: Parent - entity class represents parent of child.
 * Fields:
 * String firstName - parent first name
 * String lastName - parent last name
 */
@Entity
@Table(name = "parents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Parent extends BaseEntity {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Builder.Default
    @ManyToMany(mappedBy = "parents")
    Set<Child> children = new HashSet<>();

    public Parent(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
