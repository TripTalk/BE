package com.example.triptalk.domain.tripPlan.repository;

import com.example.triptalk.domain.tripPlan.entity.TripAccommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripAccommodationRepository extends JpaRepository<TripAccommodation, Long> {
    List<TripAccommodation> findByTripPlanId(Long tripPlanId);
}

