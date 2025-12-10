package com.example.triptalk.domain.tripPlan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

public class AmadeusResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccessToken {
        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("token_type")
        private String tokenType;

        @JsonProperty("expires_in")
        private Integer expiresIn;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FlightOffersResponse {
        private List<FlightOffer> data;
        private Object meta; // 메타 정보 (필요시 파싱)
        private Object dictionaries; // 딕셔너리 정보 (필요시 파싱)
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FlightOffer {
        private String id;
        private String type;
        private Itinerary[] itineraries;
        private Price price;
        private List<String> validatingAirlineCodes;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Itinerary {
        private Segment[] segments;
        private String duration;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Segment {
        private Departure departure;
        private Arrival arrival;
        private String carrierCode;
        private String number;
        private Aircraft aircraft;
        private String duration;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Departure {
        private String iataCode;
        private String at; // 2025-12-10T07:30:00
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Arrival {
        private String iataCode;
        private String at; // 2025-12-10T08:35:00
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Aircraft {
        private String code;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Price {
        private String currency;
        private String total;
        private String base;
    }
}

