package com.example.janperzrekrutacja.dto.parentchild;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class MultiParentChildDto {
    @Builder.Default
    private Set<ParentDto> parents = new HashSet<>();
    private ChildDto child;
    private BigDecimal totalCharge;
}
