package com.optd.common.exception;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = -5374908280684912503L;
    private List<MetaMessage> errorList;

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, List<MetaMessage> errorList) {
        super(message);
        this.errorList = errorList;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(List<MetaMessage> errorList) {
        this.errorList = errorList;
    }

    public BusinessException(MetaMessage metaMessage) {
        List<MetaMessage> errorList = new ArrayList<MetaMessage>();
        errorList.add(metaMessage);
        this.errorList = errorList;
    }
    public List<MetaMessage> getErrorList() {
        return errorList;
    }

}