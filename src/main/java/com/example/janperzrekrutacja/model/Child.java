package com.example.janperzrekrutacja.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ForeignKey;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Class: Child - entity class represents child.
 * Fields:
 * String firstName - child first name
 * String lastName - child last name
 * Long parentId - database id of parent
 * Long schoolId - database id of school
 */
@Entity
@Table(name = "children")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Child extends BaseEntity {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @ManyToMany
    @Builder.Default
    @JoinTable(name = "parent_child",
            joinColumns = @JoinColumn(name = "child_id", foreignKey = @ForeignKey(name = "FK_CHILD_PARENTS")),
            inverseJoinColumns = @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "FK_PARENT_CHILDREN")))
    private Set<Parent> parents = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_CHILD_SCHOOL"))
    private School school;
}
