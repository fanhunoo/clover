package com.fanhunoo.clover.base;

import com.fanhunoo.clover.util.Constant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private String statusCode;
    private String message;
    private Object data;

    public Result(){
    }

    public Result(Object data){
        this.statusCode = Constant.SUCCESS;
        this.message = "请求成功！";
        this.data = data;
    }

    public Result(String statusCode,String message,Object data){
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
