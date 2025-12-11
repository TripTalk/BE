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
public class TripTransportation extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String origin;

    @Column(length = 50, nullable = false)
    private String destination;

    @Column(name = "airline_name", length = 50, nullable = false)
    private String airlineName;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_plan_id", nullable = false)
    private TripPlan tripPlan;
}

