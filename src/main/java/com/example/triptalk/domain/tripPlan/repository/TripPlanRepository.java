package com.example.triptalk.domain.tripPlan.repository;

import com.example.triptalk.domain.tripPlan.entity.TripPlan;
import com.example.triptalk.domain.tripPlan.enums.TripStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TripPlanRepository extends JpaRepository<TripPlan, Long> {

    @Query("SELECT tp FROM TripPlan tp " +
           "LEFT JOIN FETCH tp.travelStyles " +
           "LEFT JOIN FETCH tp.user " +
           "WHERE tp.id = :id")
    Optional<TripPlan> findWithAllById(@Param("id") Long id);

    @Query("SELECT tp FROM TripPlan tp " +
           "WHERE tp.user.id = :userId AND tp.status = :status " +
           "AND (:cursorId IS NULL OR tp.id < :cursorId) " +
           "ORDER BY tp.id DESC")
    Slice<TripPlan> findMyTripPlans(
            @Param("userId") Long userId,
            @Param("status") TripStatus status,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}

