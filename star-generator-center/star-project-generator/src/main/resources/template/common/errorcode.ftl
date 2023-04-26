package com.${packageName}.common.errorcode;

import com.star.commons.support.code.ErrorCode;

/**
 * @Description: 业务错误码，错误信息在messages中配置
 * @author: 系统
 */
public enum ${className}ErrorCode implements ErrorCode {

    //
    COFIG_IS_ERROR(300011,"配置错误"),
    ;

    private final int code;
    private final String name;

    StatemachineErrorCode(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.name;
    }
}
