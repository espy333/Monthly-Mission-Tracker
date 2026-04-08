package com.project.ProjectApp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// CRUD
public interface MissionRepository extends JpaRepository<Mission, Long> {
    Optional<Mission> findByMonthIgnoreCase(String month);
}
