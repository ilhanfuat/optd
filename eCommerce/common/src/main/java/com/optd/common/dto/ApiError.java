package com.optd.common.dto;

import com.optd.common.exception.SeverityConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError extends ApiResponse {

    public ApiError() {
        super();
        setSeverity(SeverityConstant.ERROR);
        setLife(10000);
    }

}