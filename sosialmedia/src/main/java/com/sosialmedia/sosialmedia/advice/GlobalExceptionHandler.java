package com.sosialmedia.sosialmedia.advice;

import com.sosialmedia.sosialmedia.dto.BaseResponse;
import com.sosialmedia.sosialmedia.exception.ConflictException;
import com.sosialmedia.sosialmedia.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRunException(RuntimeException e) {
        log.error(e.getMessage());
        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(baseResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage());
        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setMessage("Access denied" + e.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(baseResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException e) {
        log.error(e.getMessage());

        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(baseResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage());
        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(e.getMessage());
        baseResponse.setSuccess(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponse);


    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException e) {
        log.error(e.getMessage());
        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(e.getMessage());
        baseResponse.setSuccess(false);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(baseResponse);
    }
}
