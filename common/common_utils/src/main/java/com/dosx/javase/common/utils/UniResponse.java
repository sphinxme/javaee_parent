package com.dosx.javase.common.utils;

import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author me and my Dokky
 * 统一返回结果的类
 */

@Data
public class UniResponse
{
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty()
    private Map<String, Object> data = new HashMap<String, Object>();

    // 禁用构造函数

    private UniResponse() {}
    private UniResponse(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    // 成功消息

    public static UniResponse ok() {
        return new UniResponse(true, ResultCode.SUCCESS, "成功");
    }

    // 失败消息

    public static UniResponse error() {
        return new UniResponse(false, ResultCode.ERROR, "失败");
    }

    // 链式编程

    public UniResponse success(Boolean success) {
        this.success = success;
        return this;
    }

    public UniResponse code(Integer code) {
        this.code = code;
        return this;
    }

    public UniResponse message(String message) {
        this.message = message;
        return this;
    }

    public UniResponse data(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public UniResponse data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

}
