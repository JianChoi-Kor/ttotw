package com.project.ttotw.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "purchase")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Purchase extends CreatedDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "wine_id", nullable = false)
    private Long wineId;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "where_to_buy", nullable = false)
    private String whereToBuy;
}
