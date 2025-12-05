package com.example.triptalk.domain.tripPlan.repository;

import com.example.triptalk.domain.tripPlan.entity.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleItemRepository extends JpaRepository<ScheduleItem, Long> {
    List<ScheduleItem> findByDailyScheduleIdOrderByOrderIndexAsc(Long dailyScheduleId);
}

