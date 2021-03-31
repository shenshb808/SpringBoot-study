package com.shb.springbootstudy.domain.vo;

import com.shb.springbootstudy.constants.ResultConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "code", example = "200")
    private int code;

    private String msg;

    private T data;

    public static <T> Result<T> ok() {
        return restResult(null, ResultConstants.SUCCESS, null);
    }

    public static <T> Result<T> ok(T data) {
        return restResult(data, ResultConstants.SUCCESS, null);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return restResult(data, ResultConstants.SUCCESS, msg);
    }

    public static <T> Result<T> failed() {
        return restResult(null, ResultConstants.FAIL, null);
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, ResultConstants.FAIL, msg);
    }

    public static <T> Result<T> failed(T data) {
        return restResult(data, ResultConstants.FAIL, null);
    }

    public static <T> Result<T> failed(T data, String msg) {
        return restResult(data, ResultConstants.FAIL, msg);
    }

    public static <T> Result<T> make(T data, int code) {
        return restResult(data, code, null);
    }

    public static <T> Result<T> make(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> Result<T> make(T data, int code, String msg) {
        return restResult(data, code, msg);
    }

    private static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
