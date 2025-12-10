package com.example.triptalk.domain.tripPlan.service;

import com.example.triptalk.domain.tripPlan.dto.AmadeusResponse;
import com.example.triptalk.global.config.AmadeusProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class AmadeusService {

    private final AmadeusProperties amadeusProperties;
    private final RestTemplate restTemplate = new RestTemplate();

    private String accessToken;
    private long tokenExpiryTime;

    /**
     * Amadeus API Access Token 발급
     */
    private String getAccessToken() {
        // 토큰이 유효하면 재사용
        if (accessToken != null && System.currentTimeMillis() < tokenExpiryTime) {
            return accessToken;
        }

        try {
            String url = amadeusProperties.getBaseUrl() + "/v1/security/oauth2/token";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "client_credentials");
            body.add("client_id", amadeusProperties.getApiKey());
            body.add("client_secret", amadeusProperties.getApiSecret());

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<AmadeusResponse.AccessToken> response = restTemplate.postForEntity(
                    url,
                    request,
                    AmadeusResponse.AccessToken.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                AmadeusResponse.AccessToken tokenResponse = response.getBody();
                this.accessToken = tokenResponse.getAccessToken();
                // 만료 시간 설정 (현재 시간 + expires_in - 60초 여유)
                this.tokenExpiryTime = System.currentTimeMillis() + (tokenResponse.getExpiresIn() - 60) * 1000L;

                log.info("Amadeus Access Token 발급 성공");
                return this.accessToken;
            }

            throw new RuntimeException("Amadeus Access Token 발급 실패");

        } catch (Exception e) {
            log.error("Amadeus Access Token 발급 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("Amadeus API 인증 실패", e);
        }
    }

    /**
     * 항공편 검색
     * @param originLocationCode 출발지 공항 코드 (IATA, 예: ICN, GMP)
     * @param destinationLocationCode 도착지 공항 코드 (IATA, 예: CJU)
     * @param departureDate 출발 날짜 (YYYY-MM-DD)
     * @param adults 성인 승객 수
     * @param max 최대 결과 수
     * @return 항공편 목록
     */
    public AmadeusResponse.FlightOffersResponse searchFlights(
            String originLocationCode,
            String destinationLocationCode,
            LocalDate departureDate,
            Integer adults,
            Integer max
    ) {
        try {
            String token = getAccessToken();
            String url = amadeusProperties.getBaseUrl() + "/v2/shopping/flight-offers";

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 쿼리 파라미터 구성
            String dateStr = departureDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            String fullUrl = String.format(
                    "%s?originLocationCode=%s&destinationLocationCode=%s&departureDate=%s&adults=%d&max=%d",
                    url, originLocationCode, destinationLocationCode, dateStr, adults, max
            );

            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<AmadeusResponse.FlightOffersResponse> response = restTemplate.exchange(
                    fullUrl,
                    HttpMethod.GET,
                    request,
                    AmadeusResponse.FlightOffersResponse.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                AmadeusResponse.FlightOffersResponse flightOffersResponse = response.getBody();

                log.info("항공편 검색 성공: {} → {}, 결과 {}건",
                        originLocationCode, destinationLocationCode,
                        flightOffersResponse.getData() != null ? flightOffersResponse.getData().size() : 0);

                return flightOffersResponse;
            }

            throw new RuntimeException("항공편 검색 실패");

        } catch (Exception e) {
            log.error("항공편 검색 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("Amadeus 항공편 검색 실패", e);
        }
    }
}

