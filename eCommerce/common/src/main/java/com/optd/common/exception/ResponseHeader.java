package com.optd.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseHeader {
    private String returnCode="";
    private String returnMessage="";
    private String returnTitle;
    private String severity;

    private List<MetaMessage> msgList;
}