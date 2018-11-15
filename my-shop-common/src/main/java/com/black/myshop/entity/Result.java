package com.black.myshop.entity;

/**
 * 处理结果的封装类
 */
public class Result {
    public static final int ERROR_STATUS=500;//失败的标记

    public static final int SUCCESS_STATUS=200;//成功的标记

    private int status;//处理结果的标记

    private String message;//返回的信息

    private Object data;//返回的obj

    public  static Result fail(String message){
        Result result = new Result();
        result.setStatus(Result.ERROR_STATUS);
        result.setMessage(message);
        return result;
    }


    public  static Result success(String message,Object object){
        Result result = new Result();
        result.setStatus(Result.SUCCESS_STATUS);
        result.setMessage(message);
        result.setData(object);
        return result;
    }


    public static int getErrorStatus() {
        return ERROR_STATUS;
    }

    public static int getSuccessStatus() {
        return SUCCESS_STATUS;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
}
