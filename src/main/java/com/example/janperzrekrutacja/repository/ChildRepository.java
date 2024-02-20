package com.example.janperzrekrutacja.repository;

import com.example.janperzrekrutacja.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class: ChildRepository - JPA repository class of Child {@link Child}
 */
@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
}
