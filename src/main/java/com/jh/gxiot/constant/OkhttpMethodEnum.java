package com.jh.gxiot.constant;

/**
 * @author Administrator
 * @version 1.0
 * @project GxIot
 * @description okhttp 请求方法
 * @date 2022/4/2 10:03:03
 */
public enum OkhttpMethodEnum {

    POST_METHOD("POST"),DELETE_METHOD("DELETE"),PUT_METHOD("PUT");

    private  String methodName;
    private OkhttpMethodEnum(String methodName){
        this.methodName = methodName;
    }
    public String getMethodName(){
        return this.methodName;
    }
}
