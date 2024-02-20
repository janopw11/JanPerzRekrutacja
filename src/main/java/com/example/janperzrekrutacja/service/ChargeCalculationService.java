package com.example.janperzrekrutacja.service;

import com.example.janperzrekrutacja.dto.attendance.ChildAttendanceDto;
import com.example.janperzrekrutacja.dto.parentchild.ChildDto;
import com.example.janperzrekrutacja.dto.parentchild.MultiParentChildDto;
import com.example.janperzrekrutacja.dto.parentchild.ParentDto;
import com.example.janperzrekrutacja.dto.parentchild.SingleParentChildDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class: ChargeCalculationService - service class for charge calculation operations.
 */
@Service
@RequiredArgsConstructor
public class ChargeCalculationService {
    @Value(value = "${charge-calculation.free-period-hour-start}")
    private int freePeriodHourStart;

    @Value(value = "${charge-calculation.free-period-minute-start}")
    private int freePeriodMinuteStart;

    @Value(value = "${charge-calculation.free-period-hour-end}")
    private int freePeriodHourEnd;

    @Value(value = "${charge-calculation.free-period-minute-end}")
    private int freePeriodMinuteEnd;

    private final static BigDecimal HOUR_MINUTES = BigDecimal.valueOf(60);

    /**
     * Sums the child charges from a set of child attendance DTOs.
     *
     * @param childAttendanceDtos - the set of child attendance DTOs containing charge information.
     * @return A set of child attendance DTOs with summed child charges.
     */
    public Set<ChildAttendanceDto> sumChildCharges(Set<ChildAttendanceDto> childAttendanceDtos) {
        Map<Long, ChildAttendanceDto> groupedByChildId = childAttendanceDtos.stream()
                .collect(Collectors.toMap(
                        dto -> dto.getChild().getId(),
                        dto -> ChildAttendanceDto.builder()
                                .child(dto.getChild())
                                .childChargeSum(dto.getChildChargeSum())
                                .build(),
                        (existingValue, newValue) -> {
                            existingValue.setChildChargeSum(existingValue.getChildChargeSum().add(newValue.getChildChargeSum()));
                            return existingValue;
                        }
                ));
        return new HashSet<>(groupedByChildId.values());
    }

    /**
     * Calculates the charges based on the entry date, exit date, and hourly price.
     *
     * @param entryDate   - the date and time of entry.
     * @param exitDate    - the date and time of exit.
     * @param hourPrice   - the price per hour.
     * @return The calculated charges based on the duration and hourly price.
     */
    public BigDecimal calculateCharges(LocalDateTime entryDate, LocalDateTime exitDate, BigDecimal hourPrice) {
        LocalDateTime freePeriodStart = LocalDateTime.of(entryDate.toLocalDate(), java.time.LocalTime.of(freePeriodHourStart, freePeriodMinuteStart));
        LocalDateTime freePeriodEnd = LocalDateTime.of(exitDate.toLocalDate(), java.time.LocalTime.of(freePeriodHourEnd, freePeriodMinuteEnd));
        BigDecimal chargeableHours = BigDecimal.ZERO;

        if (entryDate.isBefore(freePeriodStart)) {
            BigDecimal minutesBefore = BigDecimal.valueOf(ChronoUnit.MINUTES.between(entryDate, freePeriodStart));
            chargeableHours = chargeableHours.add(minutesBefore.divide(HOUR_MINUTES, RoundingMode.UP));
        }

        if (exitDate.isAfter(freePeriodEnd)) {
            BigDecimal minutesAfter = BigDecimal.valueOf(ChronoUnit.MINUTES.between(freePeriodEnd, exitDate));
            chargeableHours = chargeableHours.add(minutesAfter.divide(HOUR_MINUTES, RoundingMode.UP));
        }

        return chargeableHours.multiply(hourPrice);
    }

    public Map<Set<ParentDto>, BigDecimal> prepareParentsTotalChargeMapping(List<MultiParentChildDto> multiParentChildDtoList) {
        return multiParentChildDtoList.stream()
                .collect(Collectors.groupingBy(MultiParentChildDto::getParents,
                        Collectors.reducing(BigDecimal.ZERO, c -> c.getChild().getChildChargeSum(), BigDecimal::add)));
    }
}
