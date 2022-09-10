package com.demo.humorsource.data;

import com.demo.humorsource.enums.StatusCode;
import lombok.Data;

@Data
public class ResultData<T> {
    private int code;
    private String message;
    private T data;

    public ResultData() {
    }

    public ResultData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * success
     */
    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<T>();
        resultData.setCode(StatusCode.SUCCESS.getCode());
        resultData.setMessage(StatusCode.SUCCESS.getMessage());
        resultData.setData(data);
        return resultData;
    }

    /**
     * fail
     */
    public static ResultData<String> error(int code, String message) {
        return new ResultData<>(code, message);
    }
}
