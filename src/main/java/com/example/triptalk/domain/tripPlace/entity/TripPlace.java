package com.example.triptalk.domain.tripPlace.entity;

import com.example.triptalk.domain.tripPlace.enums.ThemeType;
import com.example.triptalk.global.apiPayload.code.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TripPlace extends BaseEntity {

    @Column(nullable = false, length = 10)
    private String region;

    @Column(nullable = false, length = 50)
    private String description;

    @Column(nullable = false)
    private Integer viewCount = 0;

    @Column(nullable = false, length = 255)
    private String imgUrl;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "trip_place_themes",  // 생성될 별도 테이블 이름
            joinColumns = @JoinColumn(name = "trip_place_id")  // 외래키 컬럼명
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "trip_place_theme")  // enum 값이 저장될 컬럼명
    private List<ThemeType> themes = new ArrayList<>();

}
