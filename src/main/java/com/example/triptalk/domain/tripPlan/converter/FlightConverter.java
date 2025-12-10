package com.example.triptalk.domain.tripPlan.converter;

import com.example.triptalk.domain.tripPlan.dto.FlightResponse;
import com.example.triptalk.domain.tripPlan.entity.Flight;
import com.example.triptalk.domain.tripPlan.util.AirportNameMapper;
import org.springframework.data.domain.Slice;

import java.util.List;

public class FlightConverter {

    /**
     * Flight 엔티티를 FlightDTO로 변환
     */
    public static FlightResponse.FlightDTO toFlightDTO(Flight flight) {
        return FlightResponse.FlightDTO.builder()
                .id(flight.getId())
                .originName(AirportNameMapper.getAirportName(flight.getOrigin()))
                .destinationName(AirportNameMapper.getAirportName(flight.getDestination()))
                .airlineName(flight.getAirlineName())
                .price(flight.getPrice())
                .departureDate(flight.getDepartureDate())
                .arrivalDate(flight.getArrivalDate())
                .imageUrl(flight.getImageUrl())
                .isOutbound(flight.getIsOutbound())
                .build();
    }

    /**
     * Slice<Flight>을 FlightListResultDTO로 변환
     */
    public static FlightResponse.FlightListResultDTO toFlightListResultDTO(Slice<Flight> slice) {
        List<FlightResponse.FlightDTO> flightList = slice.getContent().stream()
                .map(FlightConverter::toFlightDTO)
                .toList();

        // 다음 커서 ID는 마지막 항목의 ID
        Long nextCursorId = flightList.isEmpty() ?
                null :
                flightList.getLast().getId();

        return FlightResponse.FlightListResultDTO.builder()
                .flightList(flightList)
                .flightListSize(flightList.size())
                .isFirst(slice.isFirst())
                .hasNext(slice.hasNext())
                .nextCursorId(nextCursorId)
                .build();
    }
}

