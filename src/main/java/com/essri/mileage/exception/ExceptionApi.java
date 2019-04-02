package com.essri.mileage.exception;

import com.essri.mileage.event.exception.ContentNotFoundException;
import com.essri.mileage.place.exception.PlaceWriteFailException;
import com.essri.mileage.review.exception.ReviewNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionApi {
    @ExceptionHandler(value = ReviewNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleReviewNotFoundException(ReviewNotFoundException e) {
        final ErrorCode reviewNotFound = ErrorCode.REVIEW_NOT_FOUND;
        log.error(reviewNotFound.getMessage(), e.getReviewId());
        return buildError(reviewNotFound);
    }

    @ExceptionHandler(value = PlaceWriteFailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleDoNotWritePlaceException(PlaceWriteFailException e) {
        final ErrorCode doNotWrite = ErrorCode.REVIEW_NOT_FOUND;
        log.error(doNotWrite.getMessage(), e.getPlaceId(), e.getUserId());
        return buildError(doNotWrite);
    }

    @ExceptionHandler(value = ContentNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleContentNotFoundException(ContentNotFoundException e) {
        final ErrorCode contentNotFound = ErrorCode.CONTENT_NOT_FOUNT;
        log.error(contentNotFound.getMessage(),e.getReviewId());
        return buildError(contentNotFound);
    }

    private ErrorResponse buildError(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
    }
}
