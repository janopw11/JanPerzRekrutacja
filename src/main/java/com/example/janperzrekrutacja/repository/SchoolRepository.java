package com.example.janperzrekrutacja.repository;

import com.example.janperzrekrutacja.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class: SchoolRepository - JPA repository class of School {@link School}
 */
@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
