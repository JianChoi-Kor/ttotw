package com.project.ttotw.repository;

import com.project.ttotw.entity.Wine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WineRepository extends JpaRepository<Wine, Long> {

    Page<Wine> findAll(Pageable pageable);
    Optional<Wine> findById(Long id);
}
