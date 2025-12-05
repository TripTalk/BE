package com.example.triptalk.domain.tripPlan.entity;

import com.example.triptalk.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule_item")
public class ScheduleItem extends BaseEntity {

    @Column(nullable = false)
    private Integer orderIndex;

    @Column(nullable = false)
    private LocalTime time;

    @Column(length = 10, nullable = false)
    private String title;

    @Column(length = 20, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_schedule_id", nullable = false)
    private DailySchedule dailySchedule;
}

