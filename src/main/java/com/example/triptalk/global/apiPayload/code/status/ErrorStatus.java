package com.example.triptalk.global.apiPayload.code.status;


import com.example.triptalk.global.apiPayload.code.BaseErrorCode;
import com.example.triptalk.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 유저 관련 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER401", "아이디와 일치하는 사용자가 없습니다."),

    // 인증 관련 에러
    AUTH_DUPLICATE_EMAIL(HttpStatus.CONFLICT, "AUTH401", "이미 존재하는 이메일입니다."),
    AUTH_INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "AUTH402", "비밀번호가 일치하지 않습니다."),
    AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH403", "유효하지 않은 토큰입니다."),
    AUTH_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH404", "만료된 토큰입니다."),
    AUTH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AUTH405", "저장되지 않은 Refresh Token입니다."),

    // 여행 계획 관련 에러
    TRIP_PLAN_NOT_FOUND(HttpStatus.NOT_FOUND, "TRIP_PLAN401", "존재하지 않는 여행 계획입니다."),
    TRIP_PLAN_ALREADY_TRAVELED(HttpStatus.BAD_REQUEST, "TRIP_PLAN402", "이미 완료된(Traveled) 여행 계획입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}