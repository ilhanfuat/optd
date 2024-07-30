package com.optd.common.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Component
public class CustomException extends RuntimeException{

    Integer code;

    String message;

    Long referenceId;

    HttpStatus httpStatus;

    /**
     * CODE: 100 IOT REFERENCE ID ZATEN BULUNMAKTA
     */

    public CustomException(Integer code, String message, Long referenceId, HttpStatus httpStatus){
        this.code = code;
        this.message = message;
        this.referenceId = referenceId;
        this.httpStatus = httpStatus;
    }
}