package com.example.janperzrekrutacja.dto.charge;

import com.example.janperzrekrutacja.dto.parentchild.SingleParentChildDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ParentChargeSummaryDto {
    @Builder.Default
    private List<SingleParentChildDto> singleParentChildDtoList = new ArrayList<>();
}
