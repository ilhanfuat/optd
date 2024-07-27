package com.optd.service.config;

import com.optd.common.dto.ApiError;
import com.optd.common.exception.BusinessException;
import com.optd.common.exception.MetaMessage;
import com.optd.common.exception.SeverityConstant;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {



    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<?> businessExceptionControl(BusinessException businessException) {

        List<MetaMessage> errorList = businessException.getErrorList();
        List<ApiError> apiErrorList = new ArrayList<>();
        HttpStatus httpStatus = null;
        for (MetaMessage metaMessage : errorList) {
            ApiError apiError = new ApiError();
            apiError.setSeverity(SeverityConstant.ERROR);
            apiError.setSummary(metaMessage.getExplanation());
            apiError.setStatus(HttpStatus.resolve(Integer.parseInt(metaMessage.getCode())));
            apiErrorList.add(apiError);
            if (httpStatus == null || apiError.getStatus() != null)
                httpStatus = apiError.getStatus();
        }
        return new ResponseEntity<>(apiErrorList, httpStatus);
    }

}