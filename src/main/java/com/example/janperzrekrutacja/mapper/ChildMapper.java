package com.example.janperzrekrutacja.mapper;

import com.example.janperzrekrutacja.dto.parentchild.ChildDto;
import com.example.janperzrekrutacja.model.Child;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Class: ChildMapper - mapper class for mapping child relations {@link Child}.
 */
@Component
public class ChildMapper {

    public static ChildDto convertSingleChildToDto(Child child, BigDecimal childChargeSum) {
        return ChildDto.builder()
                .id(child.getId())
                .firstName(child.getFirstName())
                .lastName(child.getLastName())
                .childChargeSum(childChargeSum)
                .parents(ParentMapper.convertToParentDtos(child.getParents()))
                .build();
    }
}
