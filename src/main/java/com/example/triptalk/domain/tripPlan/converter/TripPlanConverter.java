package com.example.triptalk.domain.tripPlan.converter;

import com.example.triptalk.domain.tripPlan.dto.TripPlanResponse;
import com.example.triptalk.domain.tripPlan.entity.*;
import com.example.triptalk.domain.tripPlan.enums.TravelStyle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripPlanConverter {

    public static TripPlanResponse.TripPlanDTO toTripPlanDTO(
            TripPlan tripPlan,
            List<TripTransportation> transportations,
            List<TripAccommodation> accommodations,
            List<DailySchedule> dailySchedules,
            List<TripHighlight> highlights
    ) {
        return TripPlanResponse.TripPlanDTO.builder()
                .id(tripPlan.getId())
                .title(tripPlan.getTitle())
                .destination(tripPlan.getDestination())
                .departure(tripPlan.getDeparture())
                .startDate(tripPlan.getStartDate())
                .endDate(tripPlan.getEndDate())
                .companions(tripPlan.getCompanions())
                .budget(tripPlan.getBudget())
                .travelStyles(toTravelStyleDTO(tripPlan.getTravelStyles()))
                .status(tripPlan.getStatus().name())
                .imageUrl(tripPlan.getImgUrl())
                .transportations(toTransportationDTO(transportations))
                .accommodations(toAccommodationDTO(accommodations))
                .dailySchedules(toDailyScheduleDTO(dailySchedules))
                .highlights(toHighlightDTO(highlights))
                .build();
    }

    /**
     * TravelStyle Set의 방어적 복사본 생성
     * - null이면 빈 Set 반환
     * - null이 아니면 새로운 HashSet으로 복사 (원본 보호)
     */
    private static Set<TravelStyle> toTravelStyleDTO(Set<TravelStyle> styles) {
        return styles == null ? Set.of() : new HashSet<>(styles);
    }

    private static List<TripPlanResponse.TransportationDTO> toTransportationDTO(List<TripTransportation> list) {
        return list.stream()
                .map(t -> TripPlanResponse.TransportationDTO.builder()
                        .origin(t.getOrigin())
                        .destination(t.getDestination())
                        .name(t.getName())
                        .price(t.getPrice())
                        .build())
                .toList();
    }

    private static List<TripPlanResponse.AccommodationDTO> toAccommodationDTO(List<TripAccommodation> list) {
        return list.stream()
                .map(a -> TripPlanResponse.AccommodationDTO.builder()
                        .name(a.getName())
                        .address(a.getAddress())
                        .pricePerNight(a.getPricePerNight())
                        .build())
                .toList();
    }

    private static List<TripPlanResponse.DailyScheduleDTO> toDailyScheduleDTO(List<DailySchedule> dailySchedules) {
        return dailySchedules.stream()
                .map(ds -> TripPlanResponse.DailyScheduleDTO.builder()
                        .day(ds.getDay())
                        .date(ds.getDate())
                        .schedules(ds.getScheduleItems().stream()
                                .map(si -> TripPlanResponse.ScheduleItemDTO.builder()
                                        .orderIndex(si.getOrderIndex())
                                        .time(si.getTime())
                                        .title(si.getTitle())
                                        .description(si.getDescription())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }

    private static List<TripPlanResponse.HighlightDTO> toHighlightDTO(List<TripHighlight> list) {
        return list.stream()
                .map(h -> TripPlanResponse.HighlightDTO.builder()
                        .content(h.getContent())
                        .build())
                .toList();
    }
}

