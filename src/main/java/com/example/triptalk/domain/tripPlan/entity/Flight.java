package com.example.triptalk.domain.tripPlan.entity;

import com.example.triptalk.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String origin; // 출발 지역

    @Column(length = 50, nullable = false)
    private String destination; // 도착 지역

    @Column(length = 50, nullable = false)
    private String airlineName; // 항공사 이름

    @Column(nullable = false)
    private Integer price; // 가격 (원화)

    @Column(nullable = false)
    private LocalDate departureDate; // 출발 날짜

    @Column(nullable = false)
    private LocalDate arrivalDate; // 도착 날짜

    @Column(length = 255, nullable = false)
    private String imageUrl; // 이미지 URL

    @Column(nullable = false)
    private Boolean isOutbound; // true: 출발편, false: 귀환편
}

