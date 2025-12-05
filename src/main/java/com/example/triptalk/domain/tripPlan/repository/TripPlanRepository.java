package com.example.triptalk.domain.tripPlan.repository;

import com.example.triptalk.domain.tripPlan.entity.TripPlan;
import org.springframework.data.jpa.repository.EntityGraph;
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
}

