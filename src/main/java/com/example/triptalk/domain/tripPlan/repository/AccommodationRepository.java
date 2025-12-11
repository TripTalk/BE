package com.example.triptalk.domain.tripPlan.repository;

import com.example.triptalk.domain.tripPlan.entity.Accommodation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    /**
     * 커서 기반 숙소 목록 조회 (ID 내림차순)
     * @param cursorId 커서 ID (null이면 처음부터 조회)
     * @param pageable 페이징 정보
     * @return Slice<Accommodation>
     */
    @Query("SELECT a FROM Accommodation a " +
            "WHERE (:cursorId IS NULL OR a.id < :cursorId) " +
            "ORDER BY a.id DESC")
    Slice<Accommodation> findAllByCursor(
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}

