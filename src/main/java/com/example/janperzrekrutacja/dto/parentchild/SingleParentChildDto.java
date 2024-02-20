package com.example.janperzrekrutacja.dto.parentchild;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class SingleParentChildDto {
    private ParentDto parent;
    @Builder.Default
    private Set<ChildDto> children = new HashSet<>();
    private BigDecimal totalCharge;
}
