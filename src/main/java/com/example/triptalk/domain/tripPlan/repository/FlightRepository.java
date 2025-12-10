package com.example.triptalk.domain.tripPlan.repository;

import com.example.triptalk.domain.tripPlan.entity.Flight;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    /**
     * 커서 기반 항공권 목록 조회 (ID 내림차순)
     * @param cursorId 커서 ID (null이면 처음부터 조회)
     * @param pageable 페이징 정보
     * @return Slice<Flight>
     */
    @Query("SELECT f FROM Flight f " +
            "WHERE (:cursorId IS NULL OR f.id < :cursorId) " +
            "ORDER BY f.id DESC")
    Slice<Flight> findAllByCursor(
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}

