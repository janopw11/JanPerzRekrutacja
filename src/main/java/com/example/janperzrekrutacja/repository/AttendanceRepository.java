package com.example.janperzrekrutacja.repository;

import com.example.janperzrekrutacja.dto.attendance.ChildAttendanceDto;
import com.example.janperzrekrutacja.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Class: AttendanceRepository - JPA repository class of Attendance {@link Attendance}
 */
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT NEW com.example.janperzrekrutacja.dto.attendance.ChildAttendanceDto(" +
            "a.child, " +
            " a.entryDate, " +
            " a.exitDate," +
            " a.child.school.hourPrice) " +
            "FROM Attendance a " +
            "WHERE EXTRACT(MONTH FROM a.entryDate) = :month " +
            "AND a.child.school.id = :schoolId")
    Set<ChildAttendanceDto> findAllInSpecificMonth(Long schoolId, int month);

    @Query("SELECT NEW com.example.janperzrekrutacja.dto.attendance.ChildAttendanceDto(" +
            "a.child, " +
            " a.entryDate, " +
            " a.exitDate," +
            " a.child.school.hourPrice) " +
            "FROM Attendance a " +
            "JOIN a.child.parents par " +
            "WHERE EXTRACT(MONTH FROM a.entryDate) = :month " +
            "AND par.id = :parentId")
    Set<ChildAttendanceDto> findAllByParentIdInSpecificMonth(Long parentId, int month);
}
