package com.example.janperzrekrutacja.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * Class: School - entity class represents school
 * Fields:
 * String name - name of the school
 * BigDecimal hourPrice - price in "zloty" currency for one hour in school
 */
@Entity
@Table(name = "schools")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal hourPrice;
}
