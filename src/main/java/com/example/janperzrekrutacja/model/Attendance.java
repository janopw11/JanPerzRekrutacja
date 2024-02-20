package com.example.janperzrekrutacja.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Class: Attendance - entity class represents attendance.
 * Fields:
 * long childId - child database id
 * LocalDateTime entryDate - the time when child enter school
 * LocalDateTime exitDate - the time when child exit school
 */
@Entity
@Table(name = "attendances")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "child_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_ATTENDANCE_CHILD"))
    private Child child;
    @Column(nullable = false)
    private LocalDateTime entryDate;
    @Column(nullable = false)
    private LocalDateTime exitDate;
}
