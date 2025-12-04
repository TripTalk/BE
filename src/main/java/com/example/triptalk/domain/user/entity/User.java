package com.example.triptalk.domain.user.entity;
import com.example.triptalk.global.apiPayload.code.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 10, nullable = false)
    private String nickName;

    @Column(length = 255, nullable = false)
    private String password;

    @ColumnDefault("0")
    private Integer completedTravelCount = 0;

    @ColumnDefault("0")
    private Integer plannedTravelCount = 0;

    private String profileImgUrl;
}
