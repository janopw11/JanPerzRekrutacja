package com.example.janperzrekrutacja.mapper;

import com.example.janperzrekrutacja.dto.parentchild.ParentDto;
import com.example.janperzrekrutacja.model.Parent;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

/**
 * Class: ParentMapper - mapper class for mapping parent entity {@link Parent}.
 */
@Component
public class ParentMapper {

    public static Set<ParentDto> convertToParentDtos(Set<Parent> parents) {
        return parents.stream()
                .map(ParentMapper::convertSingleParentToDto)
                .collect(Collectors.toSet());
    }

    public static ParentDto convertSingleParentToDto(Parent parent) {
        return nonNull(parent) ? ParentDto.builder()
                .id(parent.getId())
                .firstName(parent.getFirstName())
                .lastName(parent.getLastName())
                .build() :
                ParentDto.builder().build();

    }
}
