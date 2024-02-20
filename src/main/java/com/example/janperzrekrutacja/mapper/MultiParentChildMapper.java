package com.example.janperzrekrutacja.mapper;

import com.example.janperzrekrutacja.dto.attendance.ChildAttendanceDto;
import com.example.janperzrekrutacja.dto.parentchild.MultiParentChildDto;
import com.example.janperzrekrutacja.model.Child;
import com.example.janperzrekrutacja.model.Parent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class: MultiParentChildMapper - mapper class for mapping multi parent child relations.
 */
@Component
public class MultiParentChildMapper {

    public static List<MultiParentChildDto> mapToMultiParentChildDto(Map<Child, List<ChildAttendanceDto>> childAttendances) {
        return childAttendances.entrySet().stream()
                .map(entry -> MultiParentChildMapper.buildMultiParentChildDto(
                        entry.getKey().getParents(),
                        entry.getKey(),
                        getChildChargeSum(entry.getKey(), entry.getValue())
                ))
                .collect(Collectors.toList());
    }

    private static MultiParentChildDto buildMultiParentChildDto(Set<Parent> parents, Child child, BigDecimal childCharge) {
        return MultiParentChildDto.builder()
                .parents(ParentMapper.convertToParentDtos(parents))
                .child(ChildMapper.convertSingleChildToDto(child, childCharge))
                .totalCharge(BigDecimal.ZERO)
                .build();
    }

    private static BigDecimal getChildChargeSum(Child child, List<ChildAttendanceDto> attendances) {
        return attendances.stream()
                .filter(a -> a.getChild().getId().equals(child.getId()))
                .map(ChildAttendanceDto::getChildChargeSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
