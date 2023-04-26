package com.star.commons.support.errorcode;

import com.star.commons.support.code.ErrorCode;

/**
 * 系统异常 错误码
 * @author: star
 */
public enum SystemErrorCode implements ErrorCode {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    SYSTEM_ERROR(-1, "系统内部错误"),
    HTTP_MESSAGE_NOT_READABLE(900, "HttpMessageConverter转换失败"),
    DATA_VALIDATION_FAILURE(901, "参数验证失败"),
    DATA_BIND_VALIDATION_FAILURE(902, "绑定的参数验证失败"),
    METHOD_ARGUMENT_NOT_VALID(903, "方法参数验证失败"),
    SQL_EXECUTE_FAILURE(904, "SQL语句执行出错"),
    OBJECT_IS_EMPTY(905, "对象为空"),
    QUERY_RESULT_IS_EMPTY(906, "查询结果为空"),
    ADD_OBJECT_FAIL(907, "保存对象失败"),
    UPDATE_OBJECT_FAIL(908, "修改对象失败"),
    DELETE_OBJECT_FAIL(909, "删除对象失败"),
    QUERY_OBJECT_FAIL(910, "查询对象失败"),
    QUERY_FAIL(911, "查询失败"),
    API_DISABLED(930, "api 已禁"),
    ;

    private final int code;
    private final String name;

    SystemErrorCode(final int code, final String name) {
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
