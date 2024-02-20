package com.example.janperzrekrutacja.controller;

import com.example.janperzrekrutacja.dto.attendance.ChildAttendanceDto;
import com.example.janperzrekrutacja.dto.parentchild.MultiParentChildDto;
import com.example.janperzrekrutacja.dto.parentchild.SingleParentChildDto;
import com.example.janperzrekrutacja.dto.school.SchoolChargeSummaryDto;
import com.example.janperzrekrutacja.repository.AttendanceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Class: SchoolAccountingControllerIT - testing class for controller {@link SchoolAccountingController}
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SchoolAccountingControllerIT extends SchoolAccountingTestData{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AttendanceRepository attendanceRepository;

    private final static int EXPECTED_STATUS_CODE = HttpStatus.OK.value();
    private final static String BASE_PATH = "/school-accounting/";

    @Test
    public void shouldReturnMonthSummaryForSchool() throws Exception {
        //given
        Long parentId = 2L;
        Month month = Month.FEBRUARY;
        Set<ChildAttendanceDto> mockedResult = mockSingleParentData();
        BigDecimal expectedTotalCharge = BigDecimal.valueOf(475.93);
        Long expectedChildrenSize = 1L;

        //when
        when(attendanceRepository.findAllByParentIdInSpecificMonth(anyLong(), anyInt())).thenReturn(mockedResult);
        MvcResult result = performRequestForSingleParentSummary(parentId , month);
        SingleParentChildDto response = objectMapper.readValue(result.getResponse().getContentAsString(), SingleParentChildDto.class);

        //then
        assertEquals(EXPECTED_STATUS_CODE, result.getResponse().getStatus());
        Assertions.assertEquals(expectedTotalCharge, response.getTotalCharge());
        Assertions.assertEquals(expectedChildrenSize, response.getChildren().size());
    }

    @Test
    public void shouldReturnSingleSummaryForSchool() throws Exception {
        //given
        Long schoolId = 1L;
        Month month = Month.FEBRUARY;
        List<Long> childrenIdsToCheck = List.of(3L, 4L);
        Set<ChildAttendanceDto> mockedResult = mockData();
        BigDecimal expectedTotalChargeForBond = BigDecimal.valueOf(98.76);

        //when
        when(attendanceRepository.findAllInSpecificMonth(anyLong(), anyInt())).thenReturn(mockedResult);
        MvcResult result = performRequestForSchoolSummary(schoolId, month);
        SchoolChargeSummaryDto response = objectMapper.readValue(result.getResponse().getContentAsString(), SchoolChargeSummaryDto.class);
        BigDecimal totalChargeForBond = response.getMultiParentChildDtos().stream().filter(m -> childrenIdsToCheck.contains(m.getChild().getId()))
                .map(MultiParentChildDto::getTotalCharge)
                .findFirst().orElse(BigDecimal.ZERO);

        //then
        assertEquals(EXPECTED_STATUS_CODE, result.getResponse().getStatus());
        Assertions.assertEquals(4, response.getMultiParentChildDtos().size());
        Assertions.assertEquals(expectedTotalChargeForBond, totalChargeForBond);
    }


    private MvcResult performRequestForSchoolSummary(Long schoolId, Month month) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH + schoolId + "/month-settle/" + month)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
    }
    private MvcResult performRequestForSingleParentSummary(Long parentId, Month month) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH + "/single-parent/" + parentId
                                + "/month-settle/" + month)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
    }
}
