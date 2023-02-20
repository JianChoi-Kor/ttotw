package com.project.ttotw.repository.querydsl;

import com.project.ttotw.entity.Wine;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WineQueryDsl {
    List<Wine> findAll(List<Predicate> where, Pageable pageable);
    Long getAllCount(List<Predicate> where);
    Page<Wine> findAllPaging(List<Predicate> where, Pageable pageable);
}
