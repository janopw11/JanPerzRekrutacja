package com.example.janperzrekrutacja;

import com.example.janperzrekrutacja.model.Attendance;
import com.example.janperzrekrutacja.model.Child;
import com.example.janperzrekrutacja.model.Parent;
import com.example.janperzrekrutacja.model.School;
import com.example.janperzrekrutacja.repository.AttendanceRepository;
import com.example.janperzrekrutacja.repository.ChildRepository;
import com.example.janperzrekrutacja.repository.ParentRepository;
import com.example.janperzrekrutacja.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * App is configured with local H2 database, so it is possible to save data and test it via postman
 */
@RequiredArgsConstructor
@SpringBootApplication
public class JanPerzRekrutacjaApplication {

    private final SchoolRepository schoolRepository;
    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;
    private final AttendanceRepository attendanceRepository;
    public static void main(String[] args) {

        SpringApplication.run(JanPerzRekrutacjaApplication.class, args);
    }

    /**
     * Method simulate database inserts.
     */
    public void simulateData() {
        //Schools
        School s1 = new School("School nr 1 in Java city", BigDecimal.valueOf(100));
        School s2 = new School("School nr 2 in Java city", BigDecimal.valueOf(79.99));
        School s3 = new School("School nr 3 in Java city", BigDecimal.valueOf(50));
        schoolRepository.saveAll(List.of(s1,s2,s3));

        //Parents
        Parent p1 = new Parent("Jan", "Kowalski");
        Parent p2 = new Parent("Anita", "Kowalska");
        Parent p3 = new Parent("Anna", "Wesołowska");
        Parent p4 = new Parent("Marcin", "Wesołowski");
        Parent p5 = new Parent("Andrzej", "Nowak");
        Parent p6 = new Parent("Beata", "Nowak");
        parentRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6));

        //Children
        Child c1 = new Child("Maciej", "Nowak", Set.of(p5, p6), s3);
        Child c2 = new Child("Justyna", "Wesołowska", Set.of(p3, p4), s1);
        Child c3 = new Child("Monika", "Kowalska", Set.of(p1, p2), s2);
        Child c4 = new Child("Jan", "Wesołowski", Set.of(p3, p4), s1);
        childRepository.saveAll(List.of(c1, c2, c3, c4));

        //Attendences
        Attendance a1 = new Attendance(
                c1,
                LocalDateTime.of(2024, 2, 1, 10, 0),
                LocalDateTime.of(2024, 2, 1, 16, 0));

        Attendance a2 = new Attendance(
                c1,
                LocalDateTime.of(2024, 2, 2, 6, 58),
                LocalDateTime.of(2024, 2, 2, 16, 35));

        Attendance a3 = new Attendance(
                c2,
                LocalDateTime.of(2024, 2, 3, 7, 30),
                LocalDateTime.of(2024, 2, 3, 11, 35));

        Attendance a4 = new Attendance(
                c2,
                LocalDateTime.of(2024, 2, 4, 6, 59),
                LocalDateTime.of(2024, 2, 4, 12, 1));

        Attendance a5 = new Attendance(
                c3,
                LocalDateTime.of(2024, 2, 5, 7, 0),
                LocalDateTime.of(2024, 2, 5, 12, 0));

        Attendance a6 = new Attendance(
                c3,
                LocalDateTime.of(2024, 2, 6, 6, 0),
                LocalDateTime.of(2024, 2, 6, 16,  10));

        Attendance a7 = new Attendance(
                c3,
                LocalDateTime.of(2024, 2, 7, 10, 59),
                LocalDateTime.of(2024, 2, 7, 14, 1));

        Attendance a8 = new Attendance(
                c4,
                LocalDateTime.of(2024, 2, 7, 6, 59),
                LocalDateTime.of(2024, 2, 7, 19, 1));

        Attendance a9 = new Attendance(
                c4,
                LocalDateTime.of(2024, 2, 8, 12, 59),
                LocalDateTime.of(2024, 2, 8, 16, 1));
        attendanceRepository.saveAll(List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9));
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            simulateData();
        };
    }
}
