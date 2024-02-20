package com.example.janperzrekrutacja.controller;

import com.example.janperzrekrutacja.dto.parentchild.SingleParentChildDto;
import com.example.janperzrekrutacja.dto.school.SchoolChargeSummaryDto;
import com.example.janperzrekrutacja.service.SchoolAccountingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;

/**
 * Class: SchoolAccountingController - controller class for managing school accounting operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/school-accounting")
public class SchoolAccountingController {
    private final SchoolAccountingService schoolAccountingService;

    /**
     * Retrieves the charge summary for a specific school for the given month.
     *
     * @param schoolId - the ID of the school for which the charge summary is requested.
     * @param month    - the month for which the charge summary is requested.
     * @return The charge summary DTO for the specified school and month.
     */
    @GetMapping("/{schoolId}/month-settle/{month}")
    public SchoolChargeSummaryDto settleMonth(@PathVariable Long schoolId, @PathVariable Month month) {
        return schoolAccountingService.settleSchoolMonth(schoolId, month);
    }

    /**
     * Retrieves the charge summary for a single parent for the given month.
     *
     * @param parentId - the ID of the parent for whom the charge summary is requested.
     * @param month    - the month for which the charge summary is requested.
     * @return The charge summary DTO for the specified parent and month.
     */
    @GetMapping("/single-parent/{parentId}/month-settle/{month}")
    public SingleParentChildDto settleSingleParentMonth(@PathVariable Long parentId, @PathVariable Month month) {
        return schoolAccountingService.settleSingleParentMonth(parentId, month);
    }
}
