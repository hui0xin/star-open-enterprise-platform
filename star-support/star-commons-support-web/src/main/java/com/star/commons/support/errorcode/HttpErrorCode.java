package com.star.commons.support.errorcode;

import com.star.commons.support.code.ErrorCode;

/**
 * http 错误码
 * @author: star
 */
public enum HttpErrorCode implements ErrorCode {

    BAD_REQUEST(400, "错误的请求"),
    UNAUTHORIZED(401, "未授权不能访问"),
    FORBIDDEN(403, "访问禁止"),
    NOT_FOUND(404, "请求地址不存在"),
    METHOD_NOT_ALLOWED(405, "不支持该HTTP Metho"),
    UNSUPPORTED_MEDIA_TYPE(415, "当前媒体类型不支持"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

    ;

    private final int code;
    private final String name;

    HttpErrorCode(final int code, final String name) {
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
