package com.project.ttotw.repository.querydsl;

import com.project.ttotw.entity.Wine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class WineRepositoryImpl implements WineQueryDsl {


    @Override
    public Page<Wine> findAllBySearch(String keyword1, String keyword2, boolean useAt, Pageable pageable) {
        return null;
    }
}
