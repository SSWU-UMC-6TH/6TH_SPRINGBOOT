package com.server.springStudy.apiPayload.code.status;

import com.server.springStudy.apiPayload.code.BaseCode;
import com.server.springStudy.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 성공 응답에 새로운 코드를 적고 싶다면 enum 추가
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),
    _CREATED(HttpStatus.CREATED, "COMMON201", "요청 성공 및 리소스 생성됨");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder().code(code).isSuccess(true).message(message).build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .httpStatus(httpStatus)
                .code(code)
                .isSuccess(true)
                .message(message)
                .build();
    }
}
