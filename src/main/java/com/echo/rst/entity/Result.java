package com.echo.rst.entity;

import java.io.Serializable;

/**
 * Created by echo on 16-6-11.
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1293935376154545476L;
    public static final int PAGE_SIZE = 20;

    public static enum State {
        SUCCESS,        // 成功
        FAILURE,        // 失败
        SUCCESS_PART    // 部分成功
    }


    private State state = State.SUCCESS;
    private T data;
    private String msg;

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public Result(State state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
