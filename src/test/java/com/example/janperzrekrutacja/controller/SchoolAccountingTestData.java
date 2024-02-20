package com.example.janperzrekrutacja.controller;

import com.example.janperzrekrutacja.dto.attendance.ChildAttendanceDto;
import com.example.janperzrekrutacja.model.Child;
import com.example.janperzrekrutacja.model.Parent;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class SchoolAccountingTestData {
    Set<ChildAttendanceDto> mockData() {
        ChildAttendanceDto child1Attendance1 = ChildAttendanceDto.builder()
                .child(Child.builder()
                        .id(1L)
                        .firstName("Jan")
                        .lastName("Kowalski")
                        .parents(Set.of(buildParent(1L, "Andrzej", "Kowalski"))).build())
                .entryDate(LocalDateTime.of(2024, 2, 1, 6, 59))
                .exitDate(LocalDateTime.of(2024, 2, 1, 12, 1))
                .schoolHourPrice(BigDecimal.valueOf(100))
                .build();

        ChildAttendanceDto child1Attendance2 = ChildAttendanceDto.builder()
                .child(Child.builder()
                        .id(1L)
                        .firstName("Jan")
                        .lastName("Kowalski")
                        .parents(Set.of(buildParent(1L, "Andrzej", "Kowalski")))
                        .build())
                .entryDate(LocalDateTime.of(2024, 2, 2, 5, 1))
                .exitDate(LocalDateTime.of(2024, 2, 2, 12, 2))
                .schoolHourPrice(BigDecimal.valueOf(100))
                .build();

        ChildAttendanceDto child2Attendance1 = ChildAttendanceDto.builder()
                .child(Child.builder()
                        .id(2L)
                        .firstName("Anna")
                        .lastName("Nowak")
                        .parents(Set.of(buildParent(2L, "Marek", "Nowak"), buildParent(3L, "Monika", "Nowak")))
                        .build())
                .entryDate(LocalDateTime.of(2024, 2, 1, 12, 9))
                .exitDate(LocalDateTime.of(2024, 2, 1, 18, 1))
                .schoolHourPrice(BigDecimal.valueOf(67.99))
                .build();

        ChildAttendanceDto child3Attendance1 = ChildAttendanceDto.builder()
                .child(Child.builder()
                        .id(3L)
                        .firstName("Mariusz")
                        .lastName("Bond")
                        .parents(Set.of(buildParent(4L, "Zuzanna", "Bond")))
                        .build())
                .entryDate(LocalDateTime.of(2024, 2, 1, 11, 0))
                .exitDate(LocalDateTime.of(2024, 2, 1, 13, 1))
                .schoolHourPrice(BigDecimal.valueOf(24.69))
                .build();

        ChildAttendanceDto child4Attendance1 = ChildAttendanceDto.builder()
                .child(Child.builder()
                        .id(4L)
                        .firstName("Paweł")
                        .lastName("Bond")
                        .parents(Set.of(buildParent(4L, "Zuzanna", "Bond")))
                        .build())
                .entryDate(LocalDateTime.of(2024, 2, 1, 11, 0))
                .exitDate(LocalDateTime.of(2024, 2, 1, 13, 1))
                .schoolHourPrice(BigDecimal.valueOf(24.69))
                .build();

        return Set.of(child1Attendance1, child1Attendance2, child2Attendance1, child3Attendance1, child4Attendance1);
    }

    Set<ChildAttendanceDto> mockSingleParentData() {
        ChildAttendanceDto parentData = ChildAttendanceDto.builder()
                .child(Child.builder()
                        .id(2L)
                        .firstName("Anna")
                        .lastName("Nowak")
                        .parents(Set.of(buildParent(2L, "Marek", "Nowak"), buildParent(3L, "Monika", "Nowak")))
                        .build())
                .entryDate(LocalDateTime.of(2024, 2, 1, 12, 9))
                .exitDate(LocalDateTime.of(2024, 2, 1, 18, 1))
                .schoolHourPrice(BigDecimal.valueOf(67.99))
                .build();

        return Set.of(parentData);
    }

    private Parent buildParent(Long id, String firstName, String lastName) {
        return Parent.builder().id(id).firstName(firstName).lastName(lastName).build();
    }
}
