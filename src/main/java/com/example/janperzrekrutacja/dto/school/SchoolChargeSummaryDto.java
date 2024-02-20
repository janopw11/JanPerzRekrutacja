package com.example.janperzrekrutacja.dto.school;

import com.example.janperzrekrutacja.dto.parentchild.MultiParentChildDto;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchoolChargeSummaryDto {
    @Builder.Default
    private List<MultiParentChildDto> multiParentChildDtos = new ArrayList<>();
}
