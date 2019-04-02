package com.essri.mileage.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    REVIEW_NOT_FOUND("RN_001","리뷰를 찾을 수 없습니다.",404),
    DO_NOT_WRITE_PLACE("WP_001","이미 작성한 장소입니다.", 400),
    CONTENT_NOT_FOUNT("CN_001", "작성할 내용이 없습니다.", 404);

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
