package com.example.triptalk.domain.tripPlan.repository;

import com.example.triptalk.domain.tripPlan.entity.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {

    @Query("SELECT DISTINCT ds FROM DailySchedule ds " +
           "LEFT JOIN FETCH ds.scheduleItems " +
           "WHERE ds.tripPlan.id = :tripPlanId " +
           "ORDER BY ds.day ASC")
    List<DailySchedule> findByTripPlanIdWithItemsOrderByDayAsc(@Param("tripPlanId") Long tripPlanId);
}

