package com.example.janperzrekrutacja.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Class: BaseEntity - base class for entities
 * Fields:
 * Long id - database id
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EqualsAndHashCode
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //In real world project we could add other generic fields for
    //example version to implement optimistic locking strategy.
}
