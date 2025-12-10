package com.example.triptalk.domain.tripPlan.util;

import java.util.HashMap;
import java.util.Map;

public class AirportNameMapper {

    private static final Map<String, String> AIRPORT_NAMES = new HashMap<>();

    static {
        // 한국 
        AIRPORT_NAMES.put("ICN", "인천");
        AIRPORT_NAMES.put("GMP", "김포");
        AIRPORT_NAMES.put("CJU", "제주");
        AIRPORT_NAMES.put("PUS", "김해");
        AIRPORT_NAMES.put("TAE", "대구");
        AIRPORT_NAMES.put("KWJ", "광주");
        AIRPORT_NAMES.put("RSU", "여수");
        AIRPORT_NAMES.put("USN", "울산");
        AIRPORT_NAMES.put("HIN", "사천");
        AIRPORT_NAMES.put("KPO", "포항");
        AIRPORT_NAMES.put("MWX", "무안");
        AIRPORT_NAMES.put("CJJ", "청주");
        AIRPORT_NAMES.put("YNY", "양양");

        // 일본
        AIRPORT_NAMES.put("NRT", "나리타");
        AIRPORT_NAMES.put("HND", "도쿄");
        AIRPORT_NAMES.put("KIX", "오사카");
        AIRPORT_NAMES.put("ITM", "오사카");
        AIRPORT_NAMES.put("NGO", "나고야");
        AIRPORT_NAMES.put("FUK", "후쿠오카");
        AIRPORT_NAMES.put("CTS", "삿포로");
        AIRPORT_NAMES.put("OKA", "오키나와");
        AIRPORT_NAMES.put("KMJ", "구마모토");
        AIRPORT_NAMES.put("HIJ", "히로시마");

        // 중국
        AIRPORT_NAMES.put("PVG", "상하이");
        AIRPORT_NAMES.put("SHA", "상하이");
        AIRPORT_NAMES.put("PEK", "베이징");
        AIRPORT_NAMES.put("PKX", "베이징");
        AIRPORT_NAMES.put("CAN", "광저우");
        AIRPORT_NAMES.put("SZX", "선전");
        AIRPORT_NAMES.put("XIY", "시안");
        AIRPORT_NAMES.put("CTU", "청두");
        AIRPORT_NAMES.put("WUH", "우한");
        AIRPORT_NAMES.put("HGH", "항저우");

        // 대만
        AIRPORT_NAMES.put("TPE", "타이베이");
        AIRPORT_NAMES.put("TSA", "타이베이");
        AIRPORT_NAMES.put("KHH", "가오슝");

        // 홍콩/마카오
        AIRPORT_NAMES.put("HKG", "홍콩");
        AIRPORT_NAMES.put("MFM", "마카오");

        // 동남아시아
        AIRPORT_NAMES.put("BKK", "방콕");
        AIRPORT_NAMES.put("DMK", "방콕");
        AIRPORT_NAMES.put("SIN", "싱가포르");
        AIRPORT_NAMES.put("KUL", "쿠알라룸푸르");
        AIRPORT_NAMES.put("MNL", "마닐라");
        AIRPORT_NAMES.put("SGN", "호찌민");
        AIRPORT_NAMES.put("HAN", "하노이");
        AIRPORT_NAMES.put("DAD", "다낭");
        AIRPORT_NAMES.put("CXR", "나트랑");
        AIRPORT_NAMES.put("PQC", "푸꾸옥");
        AIRPORT_NAMES.put("DPS", "발리");
        AIRPORT_NAMES.put("CGK", "자카르타");
        AIRPORT_NAMES.put("CNX", "치앙마이");
        AIRPORT_NAMES.put("HKT", "푸켓");
        AIRPORT_NAMES.put("CEB", "세부");

        // 미국
        AIRPORT_NAMES.put("JFK", "뉴욕");
        AIRPORT_NAMES.put("EWR", "뉴욕");
        AIRPORT_NAMES.put("LGA", "뉴욕");
        AIRPORT_NAMES.put("LAX", "로스앤젤레스");
        AIRPORT_NAMES.put("SFO", "샌프란시스코");
        AIRPORT_NAMES.put("ORD", "시카고");
        AIRPORT_NAMES.put("SEA", "시애틀");
        AIRPORT_NAMES.put("LAS", "라스베이거스");
        AIRPORT_NAMES.put("IAH", "휴스턴");
        AIRPORT_NAMES.put("HNL", "호놀룰루");
        AIRPORT_NAMES.put("GUM", "괌");

        // 캐나다
        AIRPORT_NAMES.put("YVR", "밴쿠버");
        AIRPORT_NAMES.put("YYZ", "토론토 피어슨");

        // 유럽
        AIRPORT_NAMES.put("LHR", "런던");
        AIRPORT_NAMES.put("LGW", "런던");
        AIRPORT_NAMES.put("CDG", "파리");
        AIRPORT_NAMES.put("ORY", "파리");
        AIRPORT_NAMES.put("FRA", "프랑크푸르트");
        AIRPORT_NAMES.put("MUC", "뮌헨");
        AIRPORT_NAMES.put("AMS", "암스테르담");
        AIRPORT_NAMES.put("FCO", "로마");
        AIRPORT_NAMES.put("MAD", "마드리드");
        AIRPORT_NAMES.put("BCN", "바르셀로나");
        AIRPORT_NAMES.put("ZRH", "취리히");
        AIRPORT_NAMES.put("VIE", "빈");
        AIRPORT_NAMES.put("PRG", "프라하");
        AIRPORT_NAMES.put("IST", "이스탄불");
        AIRPORT_NAMES.put("ATH", "아테네");

        // 중동
        AIRPORT_NAMES.put("DXB", "두바이");
        AIRPORT_NAMES.put("AUH", "아부다비");
        AIRPORT_NAMES.put("DOH", "도하");

        // 오세아니아
        AIRPORT_NAMES.put("SYD", "시드니");
        AIRPORT_NAMES.put("MEL", "멜버른");
        AIRPORT_NAMES.put("BNE", "브리즈번");
        AIRPORT_NAMES.put("AKL", "오클랜드");
        AIRPORT_NAMES.put("CNS", "케언스");

        // 남미
        AIRPORT_NAMES.put("GRU", "상파울루");
        AIRPORT_NAMES.put("EZE", "부에노스아이레스");
    }

    /**
     * IATA 코드를 한국어 명으로 변환
     * @param iataCode IATA  코드 (예: ICN, GMP)
     * @return 한국어 명 (예: 인천, 김포)
     */
    public static String getAirportName(String iataCode) {
        if (iataCode == null || iataCode.trim().isEmpty()) {
            return iataCode;
        }
        return AIRPORT_NAMES.getOrDefault(iataCode.toUpperCase(), iataCode);
    }
}

