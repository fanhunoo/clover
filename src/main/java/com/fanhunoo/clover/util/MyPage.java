package com.fanhunoo.clover.util;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * 分页返回数据格式
 * (使用layui分页默认的返回格式)
 */
public class MyPage {
    private String code;//返回状态码
    private String msg;//返回信息
    private Long count;//数据总条数
    private List<?> data;//返回的数据

    //分页成功使用的constructor
    public MyPage(List<?> data) {
        this.code = Constant.PAGE_SUCCESS;
        this.count = ((Page) data).getTotal();
        this.data = data;
    }

    public MyPage(String code, String msg, Long count, List<?> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
