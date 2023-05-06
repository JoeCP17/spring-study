package com.week.day_one.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "trade_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_name", length = 20)
    private String name;

    // 직거래 시간 table fk
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trade_time_id")
    private TradeTime tradeTime;

    private Boolean activate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
