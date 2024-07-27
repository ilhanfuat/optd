package com.optd.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class MetaMessageUtil {
    private List<MetaMessage> metaMessageList;

    public void addMetaMessageWarning(String message) {
        MetaMessage metaMessage = new MetaMessage("400", message, SeverityConstant.WARNING);
        if (metaMessageList == null) {
            metaMessageList = new ArrayList<>();
        }
        metaMessageList.add(metaMessage);
    }

    public Boolean isEmpty() {
        return metaMessageList == null || metaMessageList.isEmpty();
    }

    public List<MetaMessage> getMetaMessageList() {
        return metaMessageList;
    }

    public String getMetaMessageString() {
        StringBuffer errorString = new StringBuffer();

        metaMessageList.forEach(errorRow -> {
            errorString.append(errorRow.getExplanation());
        });

        return errorString.toString();
    }
}
