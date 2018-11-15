package com.black.myshop.test;

import java.io.Serializable;

public class BaseResult<T> implements Serializable {

    private Integer status;
    private String message;
    private T obj;

    private static final Integer STATUS_SUCESS =200;
    private static final Integer STATUS_FAIL_500 =500;
    private static final Integer STATUS_FAIL_403 =403;



    public static BaseResult success(){
      return createBaseResult(200,"成功!");
    }

    public static BaseResult success(String message){
        return createBaseResult(200,message);
    }

    public BaseResult fail(){
        return createBaseResult(500,"失败!");
    }

    public BaseResult fail(String message,Integer status){
        return createBaseResult(status,message);
    }


    public static BaseResult createBaseResult(Integer status,String message){
        BaseResult<Object> baseResult = new BaseResult<>();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        return baseResult;
    }






    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
