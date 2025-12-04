package com.example.triptalk.domain.tripPlan.entity;

import com.example.triptalk.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TripAccommodation extends BaseEntity {

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer pricePerNight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_plan_id", nullable = false)
    private TripPlan tripPlan;
}
