package com.optd.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class MetaMessage {

    private String code;
    private String explanation;
    private String severity;


    public MetaMessage() {

    }
    public MetaMessage(String code, String message, String severity) {
        this.code = code;
        this.explanation = message;
        this.severity = severity;
    }

    public MetaMessage(String code, String severity) {
        this.code = code;
        this.severity = severity;
    }

}