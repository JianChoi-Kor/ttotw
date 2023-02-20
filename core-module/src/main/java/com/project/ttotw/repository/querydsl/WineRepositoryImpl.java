package com.project.ttotw.repository.querydsl;

import com.project.ttotw.entity.Wine;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;


import static com.project.ttotw.entity.QWine.wine;

@RequiredArgsConstructor
public class WineRepositoryImpl implements WineQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Wine> findAll(List<Predicate> where, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        where.stream().filter(Objects::nonNull).forEach(builder::and);

        return queryFactory
                .select(wine)
                .from(wine)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(wine.id.desc())
                .fetch();
    }

    @Override
    public Long getAllCount(List<Predicate> where) {
        BooleanBuilder builder = new BooleanBuilder();
        where.stream().filter(Objects::nonNull).forEach(builder::and);

        return queryFactory
                .select(wine.count())
                .from(wine)
                .where(builder)
                .fetchOne();
    }

    @Override
    public Page<Wine> findAllPaging(List<Predicate> where, Pageable pageable) {
        List<Wine> content = findAll(where, pageable);
        Long count = getAllCount(where);

        return new PageImpl<>(content, pageable, count);
    }
}
