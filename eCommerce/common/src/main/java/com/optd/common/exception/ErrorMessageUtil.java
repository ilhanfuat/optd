package com.optd.common.exception;

public class ErrorMessageUtil {

    public static void checkBusinessException(MetaMessageUtil metaMessageUtil) throws BusinessException {
        if (!metaMessageUtil.isEmpty()) {
            throw new BusinessException(metaMessageUtil.getMetaMessageString(), metaMessageUtil.getMetaMessageList());
        }
    }

}
