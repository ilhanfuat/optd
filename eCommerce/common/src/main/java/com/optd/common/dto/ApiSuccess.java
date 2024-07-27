package com.optd.common.dto;

import com.optd.common.exception.SeverityConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ApiSuccess extends ApiResponse {

    public ApiSuccess(String summary, Object responseObject) {
        super();
        setSummary(summary);
        setData(responseObject);
        setStatus(HttpStatus.OK);
        setSeverity(SeverityConstant.SUCCESS);
        setLife(5000);
    }

}