package com.example.triptalk.domain.tripPlace.repository;

import com.example.triptalk.domain.tripPlace.entity.TripPlace;
import com.example.triptalk.domain.tripPlace.enums.ThemeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TripPlaceRepository extends JpaRepository<TripPlace, Long> {

    @Query("SELECT tp FROM TripPlace tp " +
           "WHERE (:theme IS NULL OR :theme MEMBER OF tp.themes) " +
           "AND (:cursorId IS NULL OR tp.id < :cursorId) " +
           "ORDER BY tp.id DESC")
    Slice<TripPlace> findByThemeWithCursor(
            @Param("theme") ThemeType theme,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}

