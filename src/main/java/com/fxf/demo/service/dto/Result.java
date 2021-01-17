package com.fxf.demo.service.dto;


public class Result<T> {

    public static final int SUCCESS = 0;
    public static final int FAILED = -1;

    private int status;
    private String statusMsg;
    private T data;

    public Result(int success, String statusMsg, T data) {
    }


    public static int getSUCCESS() {
        return SUCCESS;
    }

    public static int getFAILED() {
        return FAILED;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result failed(String statusMsg) {
        return new Result<>(FAILED, statusMsg, null);
    }

    public static <T> Result<T> ok(final T data, final String statusMsg) {
        return new Result<>(SUCCESS, statusMsg, data);
    }


}