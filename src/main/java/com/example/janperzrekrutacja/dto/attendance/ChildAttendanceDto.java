package com.example.janperzrekrutacja.dto.attendance;

import com.example.janperzrekrutacja.model.Child;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ChildAttendanceDto {
    private Child child;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private BigDecimal childChargeSum;
    private BigDecimal schoolHourPrice;

    public ChildAttendanceDto(Child child, LocalDateTime entryDate, LocalDateTime exitDate, BigDecimal schoolHourPrice) {
        this.child = child;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.schoolHourPrice = schoolHourPrice;
    }

}
