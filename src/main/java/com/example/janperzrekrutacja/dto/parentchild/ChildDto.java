package com.example.janperzrekrutacja.dto.parentchild;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ChildDto {
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal childChargeSum;
    @Builder.Default
    @JsonIgnore
    private Set<ParentDto> parents = new HashSet<>();
}
