package com.example.triptalk.domain.tripPlan.entity;

import com.example.triptalk.domain.tripPlan.enums.TravelStyle;
import com.example.triptalk.domain.tripPlan.enums.TripStatus;
import com.example.triptalk.domain.user.entity.User;
import com.example.triptalk.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TripPlan extends BaseEntity {

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 10, nullable = false)
    private String destination;

    @Column(length = 10, nullable = false)
    private String departure;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(length = 30, nullable = false)
    private String companions;

    @Column(length = 10, nullable = false)
    private String budget;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TravelStyle travelStyles;

    @Column(length = 255, nullable = false)
    private String imgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private TripStatus status = TripStatus.PLANNED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}

