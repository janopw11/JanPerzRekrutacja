package com.example.janperzrekrutacja.service;

import com.example.janperzrekrutacja.dto.attendance.ChildAttendanceDto;
import com.example.janperzrekrutacja.dto.parentchild.MultiParentChildDto;
import com.example.janperzrekrutacja.dto.parentchild.ParentDto;
import com.example.janperzrekrutacja.dto.parentchild.SingleParentChildDto;
import com.example.janperzrekrutacja.dto.school.SchoolChargeSummaryDto;
import com.example.janperzrekrutacja.mapper.MultiParentChildMapper;
import com.example.janperzrekrutacja.mapper.ParentChildMapper;
import com.example.janperzrekrutacja.model.Child;
import com.example.janperzrekrutacja.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class: SchoolAccountingService - service class for managing school accounting operations.
 */
@Service
@RequiredArgsConstructor
public class SchoolAccountingService {
    private final AttendanceRepository attendanceRepository;
    private final ChargeCalculationService chargeCalculationService;

    /**
     * Settles the charge summary for a specific school for the given month.
     *
     * @param schoolId - the ID of the school for which the charge summary is to be settled.
     * @param month    - the month for which the charge summary is to be settled.
     * @return The charge summary DTO for the specified school and month.
     */
    public SchoolChargeSummaryDto settleSchoolMonth(Long schoolId, Month month) {
        Set<ChildAttendanceDto> childAttendanceDtos = attendanceRepository.findAllInSpecificMonth(schoolId, month.getValue());
        Set<ChildAttendanceDto> childChargeDtos = getChildAttendanceWithChargeSum(childAttendanceDtos);

        return buildChargeSummaryDto(childChargeDtos);
    }

    /**
     * Settles the charge summary for a single parent for the given month.
     *
     * @param parentId - the ID of the parent for whom the charge summary is to be settled.
     * @param month    - the month for which the charge summary is to be settled.
     * @return The charge summary DTO for the specified parent and month.
     */
    public SingleParentChildDto settleSingleParentMonth(Long parentId, Month month) {
        Set<ChildAttendanceDto> childAttendanceDtos = attendanceRepository.findAllByParentIdInSpecificMonth(parentId, month.getValue());
        Set<ChildAttendanceDto> childChargeDtos = getChildAttendanceWithChargeSum(childAttendanceDtos);

        return ParentChildMapper.convertToSingleParentChildDtos(childChargeDtos, parentId);
    }

    private Set<ChildAttendanceDto> getChildAttendanceWithChargeSum(Set<ChildAttendanceDto> childAttendanceDtos) {
        setupChildChargeSum(childAttendanceDtos);
        return chargeCalculationService.sumChildCharges(childAttendanceDtos);
    }

    private SchoolChargeSummaryDto buildChargeSummaryDto(Set<ChildAttendanceDto> attendances) {
        Map<Child, List<ChildAttendanceDto>> childAttendances = attendances.stream().collect(Collectors.groupingBy(ChildAttendanceDto::getChild));
        List<MultiParentChildDto> multiParentChildDtoList = MultiParentChildMapper.mapToMultiParentChildDto(childAttendances);
        Map<Set<ParentDto>, BigDecimal> parentTotalChargeMap = chargeCalculationService.prepareParentsTotalChargeMapping(multiParentChildDtoList);
        multiParentChildDtoList.forEach(dto -> dto.setTotalCharge(parentTotalChargeMap.get(dto.getParents())));

        return new SchoolChargeSummaryDto(multiParentChildDtoList);
    }

    private void setupChildChargeSum(Set<ChildAttendanceDto> childAttendanceDtos) {
        childAttendanceDtos.forEach(e -> e.setChildChargeSum(
                calculateCharges(
                        e.getEntryDate(),
                        e.getExitDate(),
                        e.getSchoolHourPrice())));
    }

    private BigDecimal calculateCharges(LocalDateTime entryDate, LocalDateTime exitDate, BigDecimal hourPrice) {
        return chargeCalculationService.calculateCharges(entryDate, exitDate, hourPrice);
    }
}
