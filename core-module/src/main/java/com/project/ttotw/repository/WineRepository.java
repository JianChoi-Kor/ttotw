package com.project.ttotw.repository;

import com.project.ttotw.entity.Wine;
import com.project.ttotw.repository.querydsl.WineQueryDsl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WineRepository extends JpaRepository<Wine, Long>, WineQueryDsl {

    Page<Wine> findAllByUseAtOrderByIdDesc(boolean useAt, Pageable pageable);

    Page<Wine> findByOriginNameContainingOrKoreanNameContainingOrderByIdDesc(String keyword1, String keyword2, Pageable pageable);
    Optional<Wine> findByIdAndUseAt(Long id, boolean useAt);
}
