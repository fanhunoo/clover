package com.fanhunoo.clover.base;

import java.util.Map;

public class Result {
    private String statusCode;
    private String message;
    private Map<String,Object> data;

    public Result(String statusCode,String message,Map<String,Object> data){
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
