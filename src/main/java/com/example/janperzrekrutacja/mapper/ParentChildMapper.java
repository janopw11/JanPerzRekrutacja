package com.example.janperzrekrutacja.mapper;

import com.example.janperzrekrutacja.dto.attendance.ChildAttendanceDto;
import com.example.janperzrekrutacja.dto.parentchild.ChildDto;
import com.example.janperzrekrutacja.dto.parentchild.ParentDto;
import com.example.janperzrekrutacja.dto.parentchild.SingleParentChildDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class: ParentChildMapper - mapper class for mapping between parent and children.
 */
@Component
public class ParentChildMapper {
    public static SingleParentChildDto convertToSingleParentChildDtos(Set<ChildAttendanceDto> attendances, Long parentId) {
        Set<ChildDto> children = attendances.stream()
                .map(attendance -> ChildMapper.convertSingleChildToDto(
                        attendance.getChild(),
                        attendance.getChildChargeSum()))
                .collect(Collectors.toSet());

        return buildSingleParentChildDto(children, parentId);
    }

    private static SingleParentChildDto buildSingleParentChildDto(Set<ChildDto> children, Long parentId) {
        return SingleParentChildDto.builder()
                .children(children)
                .parent(retrieveParentByGivenId(getParents(children), parentId))
                .totalCharge(getTotalCharge(children))
                .build();
    }

    private static Set<ParentDto> getParents(Set<ChildDto> children) {
        return children.stream()
                .flatMap(c -> c.getParents().stream())
                .collect(Collectors.toSet());
    }

    private static BigDecimal getTotalCharge(Set<ChildDto> children) {
        return children.stream()
                .map(ChildDto::getChildChargeSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static ParentDto retrieveParentByGivenId(Set<ParentDto> parents, Long parentId) {
        return parents.stream().filter(p -> p.getId().equals(parentId))
                .findFirst()
                .orElse(null);
    }
}
