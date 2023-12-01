package com.app.backend.repository;

import com.app.backend.model.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
}
