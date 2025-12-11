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
public class Accommodation extends BaseEntity {

    @Column(length = 200, nullable = false)
    private String hotelName; // 호텔 이름

    @Column(length = 50, nullable = false)
    private String cityName; // 도시 한국어명 (예: 서울, 도쿄)

    @Column(nullable = false)
    private Integer pricePerNight; // 1박 가격 (원화)

    @Column(nullable = false)
    private LocalDate checkInDate; // 체크인 날짜

    @Column(nullable = false)
    private LocalDate checkOutDate; // 체크아웃 날짜

    @Column(length = 255, nullable = false)
    private String imageUrl; // 도시 대표 이미지 URL

}

