package com.example.triptalk.domain.tripPlan.repository;

import com.example.triptalk.domain.tripPlan.entity.TripTransportation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripTransportationRepository extends JpaRepository<TripTransportation, Long> {
    List<TripTransportation> findByTripPlanId(Long tripPlanId);
}

