package com.youda.exception;

/**
 * @CreateTime:2018/3/18 9:59
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 请求限制异常
 */
public class RequestLimitException extends Exception {

    /**
     * 声明序列版本号
     */
    private static final long serialVersionUID = 1364225358754654702L;

    /**
     * 默认构造函数
     */
    public RequestLimitException() {
    }

    /**
     * 默认构造函数带有参数
     */
    public RequestLimitException(String message) {
        super(message);
    }
}
