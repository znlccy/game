package com.youda.response.api;

/**
 * @CreateTime:2018/3/11 16:59
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 邮箱验证返回的结果
 */

public class ReturnMessage {


    /*声明错误码*/
    public static final String ERROR = "1401";

    public static final String SUCCESS = "1200";

    private String status;

    private String message;

    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ReturnMessage(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

/*enum ConstantResult {
    SUCCESS,
    ERROR
}*/
