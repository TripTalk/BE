package com.example.triptalk.domain.tripPlan.repository;

import com.example.triptalk.domain.tripPlan.entity.TripHighlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripHighlightRepository extends JpaRepository<TripHighlight, Long> {
    List<TripHighlight> findByTripPlanId(Long tripPlanId);
}

